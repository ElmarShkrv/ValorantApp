package com.chiore.valorantapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.valorantapp.data.remote.Data
import com.chiore.valorantapp.databinding.MapsLinearItemBinding

class MapsRvAdapter() :
    ListAdapter<Data, MapsRvAdapter.MapsViewHolder>(DiffUtilCallBack()) {

    class MapsViewHolder(private val binding: MapsLinearItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): MapsViewHolder {
                val binding = MapsLinearItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return MapsViewHolder(binding)
            }
        }

        fun bindToAgents(data: Data) {
            with(binding) {
                Glide.with(root).load(data.killfeedPortrait).into(mapsItemIv)
                mapsItemTv.text = data.displayName
            }
        }

        fun bind(data: Data) {
            with(binding) {
                Glide.with(root).load(data.splash).into(mapsItemIv)
                mapsItemTv.text = data.displayName
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsViewHolder {
        return MapsViewHolder(
            MapsLinearItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: MapsViewHolder, position: Int) {
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