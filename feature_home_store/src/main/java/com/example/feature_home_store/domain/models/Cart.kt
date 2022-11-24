package com.example.feature_home_store.domain.models

data class Cart(
    val basket: List<CartInfo>,
    val delivery: String,
    val id: String,
    val total: Int
)
