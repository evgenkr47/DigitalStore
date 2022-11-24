package com.example.feature_product_details.domain.repository

import com.example.core.utils.Resource
import com.example.feature_product_details.domain.models.ProductDetails
import kotlinx.coroutines.flow.Flow

interface ProductsDetailsRepository {
    fun getProductDetails(): Flow<Resource<ProductDetails>>
}