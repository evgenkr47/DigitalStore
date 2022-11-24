package com.example.feature_home_store.presentation

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.utils.Constants.TO_PRODUCT_DETAILS_FRAGMENT
import com.example.e_commerce_concept_app.feature_home_store.R
import com.example.e_commerce_concept_app.feature_home_store.databinding.BestSellerItemBinding
import com.example.feature_home_store.domain.models.BestSeller

class BestSellerAdapter(
    private val navController: NavController
): RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>() {
    inner class BestSellerViewHolder(
        val binding: BestSellerItemBinding
        ): RecyclerView.ViewHolder(binding.root)

    private val callback = object: DiffUtil.ItemCallback<BestSeller>(){
        override fun areItemsTheSame(oldItem: BestSeller, newItem: BestSeller): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BestSeller, newItem: BestSeller): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        return BestSellerViewHolder(
            BestSellerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            bestSellerContainer.setOnClickListener {
                val request = NavDeepLinkRequest.Builder
                    .fromUri(TO_PRODUCT_DETAILS_FRAGMENT.toUri())
                    .build()
                navController.navigate(request)
            }

            newPriceTextView.text = item.discountPrice.toString()
            oldPriceTextView.text = item.priceWithoutDiscount.toString()
            oldPriceTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            brandTextView.text = item.title

            Glide.with(holder.itemView.context)
                .load(item.picture)
                .into(phonePreviewImageView)

            if (item.isFavorites) heartIcon.setImageResource(
                 R.drawable.ic_heart_filled
            )
            else heartIcon.setImageResource(
                R.drawable.ic_heart_outline
            )
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}