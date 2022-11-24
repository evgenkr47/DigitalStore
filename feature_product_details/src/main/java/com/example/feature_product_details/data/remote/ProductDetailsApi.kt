package com.example.feature_product_details.data.remote

import com.example.core.utils.Constants.API_KEY_DETAILS
import com.example.feature_product_details.data.remote.dto.ProductDetailsDto
import retrofit2.http.GET

interface ProductDetailsApi {
 @GET(API_KEY_DETAILS)
 suspend fun getProductDetails(): ProductDetailsDto
}