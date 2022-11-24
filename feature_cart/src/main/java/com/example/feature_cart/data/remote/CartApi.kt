package com.example.feature_cart.data.remote

import com.example.core.utils.Constants.API_KEY_CART
import com.example.feature_cart.data.remote.dto.CartDto
import retrofit2.http.GET

interface CartApi {

    @GET(API_KEY_CART)
    suspend fun getCart(): CartDto
}