package com.example.feature_product_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_product_details.domain.use_cases.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase
): ViewModel() {

    private val _stateFlow = MutableStateFlow(ProductDetailState())
    val stateFlow = _stateFlow.asStateFlow()

    init {

    }

    private fun getProductDetails() {
        getProductDetailsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateFlow.value = ProductDetailState(item = result.data)
                }
                is Resource.Error -> {
                    _stateFlow.value = ProductDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _stateFlow.value = ProductDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}