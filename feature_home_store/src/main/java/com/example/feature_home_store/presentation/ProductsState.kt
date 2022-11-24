package com.example.feature_home_store.presentation

import com.example.feature_home_store.domain.models.AllProducts

data class ProductsState(
    val isLoading: Boolean = false,
    val allProducts: AllProducts? = null,
    val error: String = ""
)