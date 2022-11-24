package com.example.feature_cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_cart.domain.use_cases.GetCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase
): ViewModel() {

    private val _stateFlow = MutableStateFlow(CartState())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        getCart()
    }

    private fun getCart() {
        getCartUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    println(result.data)
                    _stateFlow.value = CartState(basket = result.data)
                }
                is Resource.Error -> {
                    _stateFlow.value = CartState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _stateFlow.value = CartState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}