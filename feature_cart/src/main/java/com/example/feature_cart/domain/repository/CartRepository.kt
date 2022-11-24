package com.example.feature_cart.domain.repository

import com.example.core.utils.Resource
import com.example.feature_cart.domain.models.Cart
import kotlinx.coroutines.flow.Flow

interface CartRepository {
     fun getCart(): Flow<Resource<Cart>>
}