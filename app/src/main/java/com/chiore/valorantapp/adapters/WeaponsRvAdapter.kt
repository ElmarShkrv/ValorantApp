package com.chiore.valorantapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.valorantapp.data.model.weapons.WeaponDto
import com.chiore.valorantapp.databinding.WeaponsGridItemBinding
import com.chiore.valorantapp.ui.fragments.weaponsfragment.WeaponsFragmentDirections

class WeaponsRvAdapter() :
    ListAdapter<WeaponDto, WeaponsRvAdapter.WeaponsViewHolder>(DiffUtilCallBack()) {

    class WeaponsViewHolder(private val binding: WeaponsGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: WeaponDto) {
            with(binding) {
                Glide.with(root).load(data.displayIcon).into(weaponsItemIv)
                weaponsItemTv.text = data.displayName

                itemView.setOnClickListener { view ->
                    val directions = WeaponsFragmentDirections
                        .actionWeaponsFragmentToWeaponDetailsFragment(data.uuid)
                    Navigation.findNavController(view).navigate(directions)
                }

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

    class DiffUtilCallBack : DiffUtil.ItemCallback<WeaponDto>() {
        override fun areItemsTheSame(oldItem: WeaponDto, newItem: WeaponDto): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: WeaponDto, newItem: WeaponDto): Boolean {
            return oldItem == newItem
        }

    }
}