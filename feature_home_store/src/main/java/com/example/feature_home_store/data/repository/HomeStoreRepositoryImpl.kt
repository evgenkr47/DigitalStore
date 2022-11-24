package com.example.feature_home_store.data.repository

import com.example.core.utils.Resource
import com.example.feature_home_store.data.mapper.Mapper
import com.example.feature_home_store.data.remote.ProductsApi
import com.example.feature_home_store.domain.models.AllProducts
import com.example.feature_home_store.domain.models.Cart
import com.example.feature_home_store.domain.repository.HomeStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeStoreRepositoryImpl @Inject constructor(
    private val api: ProductsApi,
    private val mapper: Mapper
): HomeStoreRepository {
    override fun getAllProducts(): Flow<Resource<AllProducts>> = flow {
        try {
            emit(Resource.Loading())
            val items = api.getAllProducts()
            emit(Resource.Success(mapper.allProductsMapper.mapFromEntity(items)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    override fun getCart(): Flow<Resource<Int>> = flow {
        try {
            emit(Resource.Loading())
            val cart = api.getCart().basket.size
            emit(Resource.Success(cart))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}