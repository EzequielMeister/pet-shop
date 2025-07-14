package com.example.tp3_petshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3_petshop.models.CartProductDetail
import com.example.tp3_petshop.models.CartProductRequest
import com.example.tp3_petshop.models.CartRequest
import com.example.tp3_petshop.models.CartResponse
import com.example.tp3_petshop.repository.CartRepository
import com.example.tp3_petshop.repository.ProductRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository,
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _cart = MutableStateFlow<CartResponse?>(null)
    val cart: StateFlow<CartResponse?> = _cart
    private var currentCartId: Int? = null
    private var userId: Int? = null

    fun setUserId(uid: Int?) {
        userId = uid
    }

    fun getCart() {
        val uid = userId ?: return
        viewModelScope.launch {
            val db = com.google.firebase.firestore.FirebaseFirestore.getInstance()
            val doc = db.collection("carts").document(uid.toString()).get().await()
            val cartFromFirestore = doc.toObject(CartResponse::class.java)
            _cart.value = cartFromFirestore ?: CartResponse(id = uid)
            currentCartId = cartFromFirestore?.id ?: uid
            println("Carrito traido de Firestore")
        }
    }

    fun addProductToCart(
        productId: Int,
        quantity: Int,
        onComplete: () -> Unit = {}
    ) {
        val uid = userId ?: return
        viewModelScope.launch {
            val product = productRepository.getProductById(productId)

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

            val total = existing.sumOf { it.price * it.quantity }
            val newCart = CartResponse(
                id = doc.toObject(CartResponse::class.java)?.id ?: 0,
                products = existing,
                total = total,
                totalProducts = existing.size,
                totalQuantity = existing.sumOf { it.quantity }
            )

            repository.saveCartToFirestore(uid.toString(), newCart) { success ->
                if (success) {
                    _cart.value = newCart
                    onComplete()
                }
            }
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

            repository.saveCartToFirestore(uid.toString(), newCart) { ok ->
                println("Producto eliminado y carrito actualizado: $ok")
                _cart.value = newCart
            }
        }
    }
}