package com.example.feature_home_store.data.remote.dto

data class HomeStoreDto(
    val id: Int,
    val is_buy: Boolean,
    val is_new: Boolean,
    val picture: String,
    val subtitle: String,
    val title: String
)
