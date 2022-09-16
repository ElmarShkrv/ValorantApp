package com.chiore.valorantapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.valorantapp.data.model.agents.AgentDto
import com.chiore.valorantapp.databinding.AgentsGridItemBinding
import com.chiore.valorantapp.ui.fragments.agentsfragment.AgentsFragmentDirections
import com.chiore.valorantapp.ui.fragments.agentsfragment.ListType

const val GRID_LAYOUT = 0
const val LINEARLAYOUT = 1

class AgentsRvAdapter(
    private var listType: ListType = ListType.GridLayout,
) : ListAdapter<AgentDto, RecyclerView.ViewHolder>(DiffUtilCallBack()) {

    fun setListType(listType: ListType) {
        this.listType = listType
    }

    class AgentsViewHolder(private val binding: AgentsGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): AgentsViewHolder {
                val binding = AgentsGridItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return AgentsViewHolder(binding)
            }
        }

        fun bind(data: AgentDto) {
            with(binding) {
                Glide.with(root).load(data.displayIcon).into(gridItemIv)
                gridItemTv.text = data.displayName

                itemView.setOnClickListener { view ->
                    val directions = AgentsFragmentDirections
                        .actionAgentsFragmentToAgentsDetailsFragment(data.uuid)
                    Navigation.findNavController(view).navigate(directions)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == GRID_LAYOUT) {
            AgentsViewHolder.from(parent)
        } else {
            MapsRvAdapter.MapsViewHolder.create(parent)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (listType) {
            ListType.GridLayout -> GRID_LAYOUT
            ListType.LinearLayout -> LINEARLAYOUT
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        if (listType == ListType.GridLayout) {
            holder as AgentsViewHolder
            holder.bind(data)
        } else {
            holder as MapsRvAdapter.MapsViewHolder
            holder.bindToAgents(data)
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<AgentDto>() {
        override fun areItemsTheSame(oldItem: AgentDto, newItem: AgentDto): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: AgentDto, newItem: AgentDto): Boolean {
            return oldItem == newItem
        }

    }
}