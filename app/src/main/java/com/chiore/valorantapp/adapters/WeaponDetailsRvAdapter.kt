package com.chiore.valorantapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.valorantapp.data.model.weapons.Skin
import com.chiore.valorantapp.databinding.WeaponDetailsSkinsItemBinding

class WeaponDetailsRvAdapter() :
    ListAdapter<Skin, WeaponDetailsRvAdapter.WeaponDetailsViewHolder>(DiffUtilCallBack()) {

    class WeaponDetailsViewHolder(val binding: WeaponDetailsSkinsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(skin: Skin) {
            with(binding) {
                Glide.with(root).load(skin.displayIcon).into(weaponDetailsSkinIv)
                Glide.with(root).load(skin.displayIcon).into(weaponDetailsExpandSkinIv)

                agentsDetailsAbilityDescription.text = skin.displayName
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponDetailsViewHolder {
        return WeaponDetailsViewHolder(
            WeaponDetailsSkinsItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: WeaponDetailsViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)

        val isVisible: Boolean = data.visibility
        holder.binding.expandableLayout.visibility = if (isVisible) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            data.visibility = !data.visibility
            notifyItemChanged(position)
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Skin>() {
        override fun areItemsTheSame(oldItem: Skin, newItem: Skin): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: Skin, newItem: Skin): Boolean {
            return oldItem == newItem
        }

    }
}