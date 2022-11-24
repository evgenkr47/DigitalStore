package com.example.feature_product_details.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce_concept_app.feature_product_details.databinding.ShopItemBinding
import com.example.feature_product_details.domain.models.ProductDetails

class ViewPagerAdapter(
    private val productItem: ProductDetails
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    class ViewPagerViewHolder(val binding: ShopItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder =
        ViewPagerViewHolder(
            ShopItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        if (position >= 1) holder.binding.shopItemContainer.visibility = View.INVISIBLE
        holder.binding.cpuTextView.text = productItem.CPU
        holder.binding.cameraTextView.text = productItem.camera
        holder.binding.ramTextView.text = productItem.ssd
        holder.binding.storageTextView.text = productItem.sd
    }

    override fun getItemCount(): Int = 3
}