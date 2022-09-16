package com.chiore.valorantapp.domain.model

import com.chiore.valorantapp.data.model.agents.Ability
import com.chiore.valorantapp.data.model.agents.Role

data class Agent(
    val abilities: List<Ability>,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val fullPortraitV2: String,
    val role: Role,
    val uuid: String
)
