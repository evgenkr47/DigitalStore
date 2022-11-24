package com.example.feature_cart.data.remote.dto

import com.example.feature_cart.domain.models.CartInfo

data class CartInfoDto(
    val id: Int,
    val images: String,
    val price: Int,
    val title: String
)



