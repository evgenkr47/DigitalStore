package com.example.feature_home_store.data.remote

import com.example.core.utils.Constants.API_KEY_ALL_PRODUCTS
import com.example.core.utils.Constants.API_KEY_CART
import com.example.feature_home_store.data.remote.dto.AllProductsDto
import com.example.feature_home_store.data.remote.dto.CartDto
import retrofit2.http.GET

interface ProductsApi {
    @GET(API_KEY_ALL_PRODUCTS)
    suspend fun getAllProducts(): AllProductsDto

    @GET(API_KEY_CART)
    suspend fun getCart(): CartDto
}