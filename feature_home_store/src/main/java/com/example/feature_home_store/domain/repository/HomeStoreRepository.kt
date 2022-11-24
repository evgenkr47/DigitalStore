package com.example.feature_home_store.domain.repository

import com.example.core.utils.Resource
import com.example.feature_home_store.domain.models.AllProducts
import kotlinx.coroutines.flow.Flow

interface HomeStoreRepository {
    fun getAllProducts():  Flow<Resource<AllProducts>>
    fun getCart(): Flow<Resource<Int>>
}