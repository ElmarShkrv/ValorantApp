package com.chiore.valorantapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.valorantapp.data.model.competitivetiers.TierDto
import com.chiore.valorantapp.databinding.TiersGridItemBinding

class TiersRvAdapter() :
    ListAdapter<TierDto, TiersRvAdapter.TiersViewHolder>(DiffUtilCallBack()) {

    class TiersViewHolder(private val binding: TiersGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tier: TierDto) {
            with(binding) {
                Glide.with(root).load(tier.largeIcon).into(tiersItemIv)
                tiersItemTv.text = tier.tierName
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiersViewHolder {
        return TiersViewHolder(
            TiersGridItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: TiersViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<TierDto>() {
        override fun areItemsTheSame(oldItem: TierDto, newItem: TierDto): Boolean {
            return oldItem.tierName == newItem.tierName
        }

        override fun areContentsTheSame(oldItem: TierDto, newItem: TierDto): Boolean {
            return oldItem == newItem
        }

    }
}