package com.example.feature_product_details.presentation

import com.example.feature_product_details.domain.models.ProductDetails

data class ProductDetailState(
    val isLoading: Boolean = false,
    val item: ProductDetails? = null,
    val error: String = ""
)
