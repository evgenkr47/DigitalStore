package com.example.feature_home_store.domain.use_cases

import com.example.feature_home_store.domain.repository.HomeStoreRepository
import javax.inject.Inject

class GetCartSizeUseCase @Inject constructor(
    private val repository: HomeStoreRepository
) {
    operator fun invoke() = repository.getCart()
}