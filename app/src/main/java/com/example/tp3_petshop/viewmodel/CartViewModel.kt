package com.example.tp3_petshop.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3_petshop.models.CartProductRequest
import com.example.tp3_petshop.models.CartRequest
import com.example.tp3_petshop.models.CartResponse
import com.example.tp3_petshop.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository,
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
            val response = repository.getUserCart(uid)
            if (response.isSuccessful) {
                _cart.value = response.body()
                currentCartId = response.body()?.id
            }
        }
    }

    fun addProductToCart(productId: Int, quantity: Int) {
        val uid = userId ?: return
        viewModelScope.launch {
            val request = CartRequest(
                userId = uid,
                products = listOf(CartProductRequest(id = productId, quantity = quantity))
            )
            val response = repository.addToCart(request)
            if (response.isSuccessful) {
                val cart = response.body()
                _cart.value = cart
                currentCartId = cart?.id
                if (cart != null) {
                    println("Llamando a saveCartToFirestore con userId=$uid y cart=$cart")
                    repository.saveCartToFirestore(uid.toString(), cart) { ok ->
                        println("Resultado guardado Firestore: $ok") }
                }
                getCart()
            }
        }
    }

    fun removeProduct(productId: Int) {
        val uid = userId ?: return
        val current = _cart.value ?: return
        val updatedProducts = current.products?.filterNot { it.id == productId } ?: return
        val cartId = current.id
        viewModelScope.launch {
            val productRequests = updatedProducts.map {
                CartProductRequest(id = it.id, quantity = it.quantity)
            }
            val response = repository.updateCart(cartId, productRequests)
            if (response.isSuccessful) {
                val cart = response.body()
                _cart.value = cart
                if (cart != null) {
                    repository.saveCartToFirestore(uid.toString(), cart) {}
                }
                getCart()
            }
        }
    }

//    fun addProductToCart(productId: Int, quantity: Int) {
//        viewModelScope.launch {
//            val request = CartRequest(
//                userId = userId,
//                products = listOf(CartProductRequest(id = productId, quantity = quantity))
//            )
//            val response = repository.addToCart(request)
//            if (response.isSuccessful) {
//                val cart = response.body()
//                _cart.value = cart
//                currentCartId = cart?.id
//                // Guarda en Firestore
//                if (cart != null) {
//                    repository.saveCartToFirestore(userId.toString(), cart) {}
//                }
//                getCart()
//            }
//        }
//    }
//
//    fun removeProduct(productId: Int) {
//        val current = _cart.value ?: return
//        val updatedProducts = current.products?.filterNot { it.id == productId } ?: return
//        val cartId = current.id
//        viewModelScope.launch {
//            val productRequests = updatedProducts.map {
//                CartProductRequest(id = it.id, quantity = it.quantity)
//            }
//            val response = repository.updateCart(cartId, productRequests)
//            if (response.isSuccessful) {
//                val cart = response.body()
//                _cart.value = cart
//                if (cart != null) {
//                    repository.saveCartToFirestore(userId.toString(), cart) {}
//                }
//                getCart()
//            }
//        }
//    }
}