package com.example.feature_home_store.domain.models

data class AllProducts(
    val bestSeller: List<BestSeller>,
    val homeStore: List<HomeStore>
)