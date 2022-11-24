package com.example.feature_cart.presentation

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.utils.BaseFragment
import com.example.core.utils.showProgressBarWhenLoading
import com.example.feature_cart.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(
    FragmentCartBinding::inflate
) {


    private val viewModel by viewModels<CartViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView() {
        lifecycleScope.launch {
            viewModel.stateFlow.collectLatest {
                binding.basketProgressBar.showProgressBarWhenLoading(it.isLoading)

                it.basket?.let { basket ->
                    binding.basketRecyclerView.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = CartAdapter()
                    }

                    binding.priceTextView.text = "$${basket.total} us"
                    binding.deliveryTextView.text = basket.delivery
                }
            }
        }
    }

    override fun setupClickListener() = with(binding) {
        backCardView.setOnClickListener { findNavController().popBackStack() }
    }


}