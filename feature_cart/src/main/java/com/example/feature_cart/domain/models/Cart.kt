package com.example.feature_cart.domain.models

data class Cart(
    val id: String,
    val basket: List<CartInfo>,
    val delivery: String,
    val total: Int
)