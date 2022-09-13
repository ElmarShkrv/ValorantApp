package com.chiore.valorantapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.valorantapp.data.remote.Data
import com.chiore.valorantapp.databinding.WeaponsGridItemBinding

class WeaponsRvAdapter() :
    ListAdapter<Data, WeaponsRvAdapter.WeaponsViewHolder>(DiffUtilCallBack()) {

    class WeaponsViewHolder(private val binding: WeaponsGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data) {
            with(binding) {
                Glide.with(root).load(data.displayIcon).into(weaponsItemIv)
                weaponsItemTv.text = data.displayName
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponsViewHolder {
        return WeaponsViewHolder(
            WeaponsGridItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: WeaponsViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }
}