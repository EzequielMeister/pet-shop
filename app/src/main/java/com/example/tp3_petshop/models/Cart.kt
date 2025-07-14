package com.example.tp3_petshop.models

data class CartRequest(
    val userId: Int,
    val products: List<CartProductRequest>
)

data class CartProductRequest(
    val id: Int,
    val quantity: Int
)

data class CartResponse(
    val id: Int = 0,
    val products: List<CartProductDetail>? = emptyList(),
    val total: Double = 0.0,
    val totalProducts: Int = 0,
    val totalQuantity: Int = 0
)

data class CartProductDetail(
    val id: Int = 0,
    val title: String = "",
    val price: Double = 0.0,
    val quantity: Int = 0,
    val total: Double = 0.0,
    val thumbnail: String = "",
    val productId: Int = 0
)

data class UpdateCartRequest(
    val merge: Boolean = true,
    val products: List<CartProductRequest>
)
