package com.example.feature_home_store.data.remote.dto

data class AllProductsDto(
    val best_seller: List<BestSellerDto>,
    val home_store: List<HomeStoreDto>
)

