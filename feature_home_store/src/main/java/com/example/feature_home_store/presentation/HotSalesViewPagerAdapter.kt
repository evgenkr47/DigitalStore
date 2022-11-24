package com.example.feature_home_store.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_home_store.databinding.HotSalesItemBinding
import com.example.feature_home_store.domain.models.HomeStore

class HotSalesViewPagerAdapter:
    RecyclerView.Adapter<HotSalesViewPagerAdapter.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(
        val binding: HotSalesItemBinding
        ): RecyclerView.ViewHolder(binding.root)

    private val callback = object: DiffUtil.ItemCallback<HomeStore>(){
        override fun areItemsTheSame(oldItem: HomeStore, newItem: HomeStore): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeStore, newItem: HomeStore): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(
            HotSalesItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val item = differ.currentList[position]
        Glide.with(holder.itemView.context)
            .load(item.picture)
            .into(holder.binding.hotSalesImageView)

        if (item.isNew) holder.binding.newCardView.visibility = View.VISIBLE
        else holder.binding.newCardView.visibility = View.INVISIBLE

        holder.binding.titleTextView.text = item.title
        holder.binding.subTitleTextView.text = item.subtitle


        if (position == 1) {
            holder.binding.titleTextView.visibility = View.INVISIBLE
            holder.binding.subTitleTextView.visibility = View.INVISIBLE
        } else {
            holder.binding.titleTextView.visibility = View.VISIBLE
            holder.binding.subTitleTextView.visibility = View.VISIBLE
        }

        if (item.isBuy) holder.binding.buyNowButton.visibility = View.VISIBLE
        else holder.binding.buyNowButton.visibility = View.GONE

    }

    override fun getItemCount(): Int = differ.currentList.size
}