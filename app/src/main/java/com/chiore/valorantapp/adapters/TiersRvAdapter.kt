package com.chiore.valorantapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.valorantapp.data.remote.Data
import com.chiore.valorantapp.data.remote.Tier
import com.chiore.valorantapp.databinding.TiersGridItemBinding
import com.chiore.valorantapp.databinding.WeaponsGridItemBinding

class TiersRvAdapter() :
    ListAdapter<Tier, TiersRvAdapter.TiersViewHolder>(DiffUtilCallBack()) {

    class TiersViewHolder(private val binding: TiersGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tier: Tier) {
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

    class DiffUtilCallBack : DiffUtil.ItemCallback<Tier>() {
        override fun areItemsTheSame(oldItem: Tier, newItem: Tier): Boolean {
            return oldItem.tierName == newItem.tierName
        }

        override fun areContentsTheSame(oldItem: Tier, newItem: Tier): Boolean {
            return oldItem == newItem
        }

    }
}