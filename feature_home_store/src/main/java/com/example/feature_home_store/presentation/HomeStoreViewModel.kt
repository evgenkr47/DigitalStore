package com.example.feature_home_store.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_home_store.domain.use_cases.GetAllProductsUseCase
import com.example.feature_home_store.domain.use_cases.GetCartSizeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeStoreViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getCartSizeUseCase: GetCartSizeUseCase

): ViewModel(){

    private val _stateFlow = MutableStateFlow(ProductsState())
    val stateFlow = _stateFlow.asStateFlow()

    private val _basketSizeStateFlow = MutableStateFlow(CartSizeState())
    val basketSizeStateFlow = _basketSizeStateFlow.asStateFlow()

    init {

    }

    private fun getAllProducts() {
        getAllProductsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateFlow.value = ProductsState(allProducts = result.data)
                }
                is Resource.Error -> {
                    _stateFlow.value = ProductsState(
                        error = result.message
                            ?: "An unexpected error occurred while getting all products"
                    )
                }
                is Resource.Loading -> {
                    _stateFlow.value = ProductsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getBasketSize() {
        getCartSizeUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _basketSizeStateFlow.value = CartSizeState(basketSize = result.data ?: 0)
                }
                is Resource.Error -> {
                    _basketSizeStateFlow.value = CartSizeState(
                        error = result.message
                            ?: "An unexpected error occurred while loading basket size"
                    )
                }
                is Resource.Loading -> {
                    _basketSizeStateFlow.value = CartSizeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }




}