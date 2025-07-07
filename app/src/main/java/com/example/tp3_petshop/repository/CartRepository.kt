package com.example.tp3_petshop.repository

import com.example.tp3_petshop.models.CartProductRequest
import com.example.tp3_petshop.models.CartRequest
import com.example.tp3_petshop.models.CartResponse
import com.example.tp3_petshop.models.UpdateCartRequest
import javax.inject.Inject
import javax.inject.Singleton
import com.example.tp3_petshop.network.RetrofitInstance
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Response

@Singleton
class CartRepository @Inject constructor(){


    fun saveCartToFirestore(userId: String, cart: CartResponse, onResult: (Boolean) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("carts").document(userId)
            .set(cart)
            .addOnSuccessListener {
                println("Firestore: carrito guardado OK")
                onResult(true)
            }
            .addOnFailureListener { e ->
                println("Firestore error: ${e.message}")
                e.printStackTrace()
                onResult(false)
            }
    }

    suspend fun addToCart(request: CartRequest): Response<CartResponse> {
        return RetrofitInstance.cartApi.addToCart(request)
    }

    suspend fun getUserCart(userId: Int): Response<CartResponse> {
        return RetrofitInstance.cartApi.getUserCart(userId)
    }

    suspend fun updateCart(cartId: Int, products: List<CartProductRequest>): Response<CartResponse> {
        val request = UpdateCartRequest(
            merge = true,
            products = products
        )
        return RetrofitInstance.cartApi.updateCart(cartId, request)
    }

}