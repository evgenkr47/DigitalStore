package com.example.feature_product_details.presentation

import android.annotation.SuppressLint
import android.view.View
import androidx.core.graphics.toColorInt
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.core.utils.*
import com.example.feature_product_details.R
import com.example.feature_product_details.databinding.FragmentProductDetailsBinding
import com.example.feature_product_details.domain.models.ProductDetails
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailsFragment: BaseFragment<FragmentProductDetailsBinding>(
    FragmentProductDetailsBinding::inflate
) {
    private val viewModel by viewModels<ProductDetailsViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView() {

        lifecycleScope.launch {
            viewModel.stateFlow.collectLatest {
                binding.productDetailsProgressBar.showProgressBarWhenLoading(it.isLoading)
                if (it.isLoading) {
                    binding.ratingBar.visibility = View.INVISIBLE
                    binding.detailsLinearLayout.visibility = View.INVISIBLE
                } else {
                    binding.ratingBar.visibility = View.VISIBLE
                    binding.detailsLinearLayout.visibility = View.VISIBLE
                }

                it.item?.let { item ->
                    val productDetailsViewPagerAdapter = ProductDetailsViewPagerAdapter(
                        images = item.images
                    )

                    binding.phonePreviewViewPager.adapter = productDetailsViewPagerAdapter
                    binding.brandTextView.text = item.title

                    if (item.isFavorites) binding.heartIcon.setImageResource(
                        com.example.core.R.drawable.ic_heart_filled
                    )
                    else binding.heartIcon.setImageResource(
                        com.example.core.R.drawable.ic_heart_outline
                    )

                    setViewPagerAdapter(item)

                    binding.firstColorCardView.setCardBackgroundColor(item.color[0].toColorInt())
                    binding.secondColorCardView.setCardBackgroundColor(item.color[1].toColorInt())

                    binding.firstCapacityTextView.text = "${item.capacity[0]} gb"
                    binding.secondCapacityTextView.text = "${item.capacity[1]} gb"

                    binding.priceTextView.text = "$${item.price}.00"
                }
            }
        }
    }

    override fun setupClickListener() = with(binding) {
        backCardView.setOnClickListener { findNavController().popBackStack() }

        firstCapacityTextView.setOnClickListener {
            firstCapacityCardView.setCardBgColor(requireContext(), com.example.core.R.color.primary)
            secondCapacityCardView.setCardBgColor(requireContext(), R.color.white)
            firstCapacityTextView.setTextColor(requireContext(), R.color.white)
            secondCapacityTextView.setTextColor(requireContext(), com.example.core.R.color.text_gray)
            secondCapacityCardView.cardElevation = 0f
        }

        secondCapacityTextView.setOnClickListener {
            secondCapacityCardView.setCardBgColor(requireContext(), com.example.core.R.color.primary)
            firstCapacityCardView.setCardBgColor(requireContext(), R.color.white)
            secondCapacityTextView.setTextColor(requireContext(), R.color.white)
            firstCapacityTextView.setTextColor(requireContext(), com.example.core.R.color.text_gray)
            firstCapacityCardView.cardElevation = 0f
        }

        firstColorCardView.setOnClickListener {
            secondColorCheckIcon.visibility = View.GONE
            firstColorCheckIcon.visibility = View.VISIBLE
        }

        secondColorCardView.setOnClickListener {
            firstColorCheckIcon.visibility = View.GONE
            secondColorCheckIcon.visibility = View.VISIBLE
        }

        basketCardView.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri(Constants.TO_BASKET_FRAGMENT.toUri())
                .build()
            findNavController().navigate(request)
        }
    }

    private fun setViewPagerAdapter(item: ProductDetails) {
        val adapter = ViewPagerAdapter(item)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text =
                    requireContext().getText(R.string.shop)
                1 -> tab.text =
                    requireContext().getText(R.string.details)
                2 -> tab.text =
                    requireContext().getText(R.string.features)
            }
        }.attach()

        binding.tabLayout.changeSelectedTabItemFontFamily(0, true)

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.tabLayout.changeSelectedTabItemFontFamily(tab.position, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                binding.tabLayout.changeSelectedTabItemFontFamily(tab.position, false)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

}