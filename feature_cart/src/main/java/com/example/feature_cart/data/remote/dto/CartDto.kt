package com.example.feature_cart.data.remote.dto

import com.example.feature_cart.domain.models.Cart

data class CartDto(
    val basket: List<CartInfoDto>,
    val delivery: String,
    val id: String,
    val total: Int
)

