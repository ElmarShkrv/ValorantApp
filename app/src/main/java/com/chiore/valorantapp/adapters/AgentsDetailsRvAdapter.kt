package com.chiore.valorantapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.valorantapp.R
import com.chiore.valorantapp.data.model.agents.Ability
import com.chiore.valorantapp.databinding.AgentsDetailsItemBinding

class AgentsDetailsRvAdapter() :
    ListAdapter<Ability, AgentsDetailsRvAdapter.AgentDetailsViewHolder>(DiffUtilCallBack()) {

    class AgentDetailsViewHolder(val binding: AgentsDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ability: Ability) {
            with(binding) {
                Glide.with(root).load(ability.displayIcon).into(agentsDetailsAbilityIv)
                agentsDetailsAbilityNameTv.text = ability.displayName
                agentsDetailsAbilityDescription.text = ability.description
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentDetailsViewHolder {
        return AgentDetailsViewHolder(
            AgentsDetailsItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: AgentDetailsViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)

        val isVisible: Boolean = data.visibility
        holder.binding.expandableLayout.visibility = if (isVisible) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            data.visibility = !data.visibility
            notifyItemChanged(position)
        }

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Ability>() {
        override fun areItemsTheSame(oldItem: Ability, newItem: Ability): Boolean {
            return oldItem.displayName == newItem.displayName
        }

        override fun areContentsTheSame(oldItem: Ability, newItem: Ability): Boolean {
            return oldItem == newItem
        }

    }

}