package com.example.feature_product_details.di

import com.example.core.utils.Constants
import com.example.feature_product_details.data.mapper.ProductsDetailsMapper
import com.example.feature_product_details.data.remote.ProductDetailsApi
import com.example.feature_product_details.data.repository.ProductsDetailsRepositoryImpl
import com.example.feature_product_details.domain.repository.ProductsDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductDetailsModule {
    @Provides
    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): ProductDetailsApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(ProductDetailsApi::class.java)

    @Provides
    @Singleton
    fun provideStoreRepository(
        api: ProductDetailsApi,
        mapper: ProductsDetailsMapper
    ): ProductsDetailsRepository = ProductsDetailsRepositoryImpl(api, mapper)
}