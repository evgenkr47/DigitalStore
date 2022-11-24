package com.example.feature_home_store.presentation

data class CartSizeState(
    val isLoading: Boolean = false,
    val basketSize: Int = 0,
    val error: String = ""
)
