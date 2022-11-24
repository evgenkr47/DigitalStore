package com.example.feature_product_details.domain.use_cases

import com.example.feature_product_details.domain.repository.ProductsDetailsRepository
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val repository: ProductsDetailsRepository
){
    operator fun invoke() = repository.getProductDetails()
}