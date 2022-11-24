package com.example.feature_cart.di

import com.example.core.utils.Constants.BASE_URL
import com.example.feature_cart.data.mapper.CartInfoMapper
import com.example.feature_cart.data.mapper.CartMapper
import com.example.feature_cart.data.mapper.Mapper
import com.example.feature_cart.data.remote.CartApi
import com.example.feature_cart.data.repository.CartRepositoryImpl
import com.example.feature_cart.domain.repository.CartRepository
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
object CartModule {
    @Provides
    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): CartApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(CartApi::class.java)

    @Provides
    @Singleton
    fun provideCartMapper(): Mapper {
        return Mapper(
            cartInfoMapper = CartInfoMapper(),
            cartMapper = CartMapper(mapper = CartInfoMapper()),
        )
    }

    @Provides
    @Singleton
    fun provideCartRepository(
        api: CartApi,
        mapper: CartMapper
    ): CartRepository = CartRepositoryImpl(api, mapper)
}