package com.example.feature_home_store.data.remote.dto

data class CartDto(
    val basket: List<CartInfoDto>,
    val delivery: String,
    val id: String,
    val total: Int
)
