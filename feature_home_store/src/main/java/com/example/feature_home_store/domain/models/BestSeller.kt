package com.example.feature_home_store.domain.models

data class BestSeller(
    val id: Int,
    val discountPrice: Int,
    val isFavorites: Boolean,
    val picture: String,
    val priceWithoutDiscount: Int,
    val title: String
)