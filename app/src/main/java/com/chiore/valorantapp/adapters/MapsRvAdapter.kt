package com.chiore.valorantapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.valorantapp.data.model.agents.AgentDto
import com.chiore.valorantapp.data.model.maps.MapDto
import com.chiore.valorantapp.databinding.MapsLinearItemBinding
import com.chiore.valorantapp.ui.fragments.agentsfragment.AgentsFragmentDirections
import com.chiore.valorantapp.ui.fragments.mapsfragment.MapsFragmentDirections

class MapsRvAdapter() :
    ListAdapter<MapDto, MapsRvAdapter.MapsViewHolder>(DiffUtilCallBack()) {

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

        fun bindToAgents(data: AgentDto) {
            with(binding) {
                Glide.with(root).load(data.killfeedPortrait).into(mapsItemIv)
                mapsItemTv.text = data.displayName

                itemView.setOnClickListener { view ->
                    val directions = AgentsFragmentDirections
                        .actionAgentsFragmentToAgentsDetailsFragment(data.uuid)
                    Navigation.findNavController(view).navigate(directions)
                }

            }
        }

        fun bind(data: MapDto) {
            with(binding) {
                Glide.with(root).load(data.splash).into(mapsItemIv)
                mapsItemTv.text = data.displayName

                itemView.setOnClickListener { view ->
                    val directions = MapsFragmentDirections
                        .actionMapsFragmentToMapsDetailsFragment(data.uuid)
                    Navigation.findNavController(view).navigate(directions)
                }

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

    class DiffUtilCallBack : DiffUtil.ItemCallback<MapDto>() {
        override fun areItemsTheSame(oldItem: MapDto, newItem: MapDto): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: MapDto, newItem: MapDto): Boolean {
            return oldItem == newItem
        }

    }

}