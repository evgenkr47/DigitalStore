package com.example.feature_cart.data.repository

import com.example.core.utils.Resource
import com.example.feature_cart.data.mapper.CartMapper
import com.example.feature_cart.data.remote.CartApi
import com.example.feature_cart.domain.models.Cart
import com.example.feature_cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val api: CartApi,
    private val mapper: CartMapper
): CartRepository {
    override fun getCart(): Flow<Resource<Cart>> = flow {
        try {
            emit(Resource.Loading())
            val cart = api.getCart()
            emit(Resource.Success(mapper.mapFromEntity(cart)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}