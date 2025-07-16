package com.example.tp3_petshop.models

import com.google.firebase.Timestamp

data class Order(
    val userId: String,
    val items: List<CartProductDetail>,
    val total: Double,
    val paymentMethod: String,
    val timestamp: Timestamp = Timestamp.now()
)
