package com.example.tp3_petshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3_petshop.models.CartProductDetail
import com.example.tp3_petshop.models.CartProductRequest
import com.example.tp3_petshop.models.CartRequest
import com.example.tp3_petshop.models.CartResponse
import com.example.tp3_petshop.models.Order
import com.example.tp3_petshop.repository.CartRepository
import com.example.tp3_petshop.repository.ProductRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.jvm.java

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository,
    private val productRepository: ProductRepository,
    private val firestore: FirebaseFirestore
) : ViewModel() {
    // acá guardamos el cart y lo ponemo como StateFlow para el front
    private val _cart = MutableStateFlow<CartResponse?>(null)
    val cart: StateFlow<CartResponse?> = _cart
    private var currentCartId: Int? = null
    private var userId: Int? = null

    fun setUserId(uid: Int?) {
        userId = uid
    }

    // trae el cart desde Firestore o crea uno vacio si no existe
    fun getCart() {
        val uid = userId ?: return
        viewModelScope.launch {
            val db = com.google.firebase.firestore.FirebaseFirestore.getInstance()
            val doc = db.collection("carts").document(uid.toString()).get()
                .await() // pedimos el doc con la colección "carts"
            val cartFromFirestore = doc.toObject(CartResponse::class.java)

            // si no habia nada, creamos un CartResponse default con el ID del user
            _cart.value = cartFromFirestore ?: CartResponse(id = uid)
            currentCartId = cartFromFirestore?.id ?: uid
            println("Carrito traido de Firestore")
        }
    }

    // agrega un producto (o aumenta quantity) y guarda en Firestore

    fun addProductToCart(
        productId: Int,
        quantity: Int,
        onComplete: () -> Unit = {}
    ) {
        val uid = userId ?: return
        viewModelScope.launch {
            val product = productRepository.getProductById(productId)

            // leemos el cart actual directo de Firestore
            val db = FirebaseFirestore.getInstance()
            val doc = db.collection("carts").document(uid.toString()).get().await()
            val existing = doc.toObject(CartResponse::class.java)?.products?.toMutableList()
                ?: mutableListOf()

            val idx = existing.indexOfFirst { it.id == productId }
            if (idx >= 0) {
                val old = existing[idx]
                val newQty = old.quantity + quantity
                existing[idx] = old.copy(
                    quantity = newQty,
                    total = product.price * newQty
                )
            } else {
                // si no existe cart, lo metemos nuevo
                existing.add(
                    CartProductDetail(
                        id = product.id,
                        title = product.title,
                        price = product.price,
                        quantity = quantity,
                        total = product.price * quantity,
                        thumbnail = product.thumbnail,
                        productId = product.id
                    )
                )
            }

            // actualizar el total del cart
            val total = existing.sumOf { it.price * it.quantity }
            val newCart = CartResponse(
                id = doc.toObject(CartResponse::class.java)?.id ?: 0,
                products = existing,
                total = total,
                totalProducts = existing.size,
                totalQuantity = existing.sumOf { it.quantity }
            )

            // guarda el cart en Firestore y actualiza el flujo
            repository.saveCartToFirestore(uid.toString(), newCart) { success ->
                if (success) {
                    _cart.value = newCart
                    onComplete()
                }
            }
        }
    }

    // para persistir el carrito de compras al hacer checkout
    fun persistCart(onComplete: () -> Unit = {}) {
        val uid = userId ?: return
        val current = _cart.value ?: return
        repository.saveCartToFirestore(uid.toString(), current) { success ->
            if (success) onComplete()
        }
    }

    fun removeProduct(productId: Int) {
        val uid = userId ?: return
        val current = _cart.value ?: return
        val updatedProducts = current.products?.filterNot { it.id == productId } ?: return
        viewModelScope.launch {
            val total = updatedProducts.sumOf { it.price * it.quantity }
            val totalQuantity = updatedProducts.sumOf { it.quantity }
            val totalProducts = updatedProducts.size

            val newCart = CartResponse(
                id = current.id,
                products = updatedProducts,
                total = total,
                totalProducts = totalProducts,
                totalQuantity = totalQuantity
            )

            // lo guardamos y actualizamos el state
            repository.saveCartToFirestore(uid.toString(), newCart) { ok ->
                println("Producto eliminado y carrito actualizado: $ok")
                _cart.value = newCart
            }
        }
    }

    fun createOrder(order: Order, onComplete: () -> Unit) {
        firestore.collection("orders")
            .add(order)
            .addOnSuccessListener { onComplete() }
            .addOnFailureListener { /* manejar error si hace falta */ }
    }

    // vaciar cart al hacer checkout
    fun clearCart(onComplete: () -> Unit = {}) {
        val uid = userId ?: return
        viewModelScope.launch {
            val emptyCart = CartResponse(
                id = uid,
                products = emptyList(),
                total = 0.0,
                totalProducts = 0,
                totalQuantity = 0
            )
            repository.saveCartToFirestore(uid.toString(), emptyCart) { success ->
                if (success) {
                    _cart.value = emptyCart
                    onComplete()
                }
            }
        }
    }

}