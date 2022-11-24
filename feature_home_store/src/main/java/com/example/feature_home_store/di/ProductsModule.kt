package com.example.feature_home_store.di

import com.example.core.utils.Constants
import com.example.feature_home_store.data.mapper.AllProductsMapper
import com.example.feature_home_store.data.mapper.BestSellerMapper
import com.example.feature_home_store.data.mapper.HomeStoreMapper
import com.example.feature_home_store.data.mapper.Mapper
import com.example.feature_home_store.data.remote.ProductsApi
import com.example.feature_home_store.data.repository.HomeStoreRepositoryImpl
import com.example.feature_home_store.domain.repository.HomeStoreRepository
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
object ProductsModule {
    @Provides
    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): ProductsApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(ProductsApi::class.java)

    @Provides
    @Singleton
    fun provideProductsMapper(): Mapper {
        return Mapper(
            bestSellerMapper = BestSellerMapper(),
            homeStoreMapper = HomeStoreMapper(),
            allProductsMapper = AllProductsMapper(
                bestSellerMapper = BestSellerMapper(),
            homeStoreMapper = HomeStoreMapper()
            )
        )
    }

    @Provides
    @Singleton
    fun provideHomeStoreRepository(
        api: ProductsApi,
        mapper: Mapper
    ): HomeStoreRepository = HomeStoreRepositoryImpl(api, mapper)
}