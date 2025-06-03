package com.example.tp3_petshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3_petshop.models.CartProductRequest
import com.example.tp3_petshop.models.CartRequest
import com.example.tp3_petshop.models.CartResponse
import com.example.tp3_petshop.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val repository = CartRepository()
    private val _cart = MutableStateFlow<CartResponse?>(null)
    val cart: StateFlow<CartResponse?> = _cart
    private var currentCartId: Int? = null

    fun addProductToCart(productId: Int, quantity: Int) {
        viewModelScope.launch {
            val request = CartRequest(
                userId = 1,
                products = listOf(CartProductRequest(id = productId, quantity = quantity))
            )
            val response = repository.addToCart(request)
            if (response.isSuccessful) {
                _cart.value = response.body()
                currentCartId = response.body()?.id
            }
        }
    }

    fun removeProduct(productId: Int) {
        val current = _cart.value ?: return
        val updatedProducts = current.products?.filterNot { it.id == productId }
        val updatedCart = current.copy(products = updatedProducts)
        _cart.value = updatedCart
    }
}