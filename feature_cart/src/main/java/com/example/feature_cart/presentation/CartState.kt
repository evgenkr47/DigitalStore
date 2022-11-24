package com.example.feature_cart.presentation

import com.example.feature_cart.domain.models.Cart

data class CartState(
    val isLoading: Boolean = false,
    val basket: Cart? = null,
    val error: String = ""
)