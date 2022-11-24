package com.example.feature_product_details.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce_concept_app.feature_product_details.databinding.ProductDetailsItemBinding

class ProductDetailsViewPagerAdapter(
    private val images: List<String>,
) :
    RecyclerView.Adapter<ProductDetailsViewPagerAdapter.ViewPagerViewHolder>() {

    class ViewPagerViewHolder(val binding: ProductDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder =
        ViewPagerViewHolder(
            ProductDetailsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val image = images[position]
        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.binding.phonePreviewImageView)
    }

    override fun getItemCount(): Int = images.size
}