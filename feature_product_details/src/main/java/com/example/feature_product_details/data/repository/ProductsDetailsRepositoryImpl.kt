package com.example.feature_product_details.data.repository

import com.example.core.utils.Resource
import com.example.feature_product_details.data.mapper.ProductsDetailsMapper
import com.example.feature_product_details.data.remote.ProductDetailsApi
import com.example.feature_product_details.domain.models.ProductDetails
import com.example.feature_product_details.domain.repository.ProductsDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductsDetailsRepositoryImpl @Inject constructor(
    private val api: ProductDetailsApi,
    private val mapper: ProductsDetailsMapper
): ProductsDetailsRepository {
    override fun getProductDetails(): Flow<Resource<ProductDetails>> = flow {
        try {
            emit(Resource.Loading())
            val item = api.getProductDetails()
            emit(Resource.Success(mapper.mapFromEntity(item)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}