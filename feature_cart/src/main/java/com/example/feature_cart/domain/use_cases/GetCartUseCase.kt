package com.example.feature_cart.domain.use_cases

import com.example.feature_cart.domain.repository.CartRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
     operator fun invoke() = repository.getCart()
}