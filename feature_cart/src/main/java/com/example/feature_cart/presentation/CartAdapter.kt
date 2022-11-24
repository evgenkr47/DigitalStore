package com.example.feature_cart.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce_concept_app.feature_cart.databinding.CartItemBinding
import com.example.feature_cart.domain.models.CartInfo

class CartAdapter: RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    inner class CartViewHolder(val binding: CartItemBinding): RecyclerView.ViewHolder(binding.root)

    private val callback = object : DiffUtil.ItemCallback<CartInfo>() {
        override fun areItemsTheSame(oldItem: CartInfo, newItem: CartInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartInfo, newItem: CartInfo): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            CartItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = differ.currentList[position]
        holder.binding.apply {
            brandTextView.text = cart.title
            priceTextView.text = "$${cart.price}.00"
            Glide.with(holder.itemView.context)
                .load(cart.images)
                .into(phonePreviewImageView)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size



}