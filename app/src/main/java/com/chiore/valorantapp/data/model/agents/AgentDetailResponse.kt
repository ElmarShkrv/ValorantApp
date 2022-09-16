package com.chiore.valorantapp.data.model.agents

import com.google.gson.annotations.SerializedName

data class AgentDetailResponse(
    @SerializedName("data")
    val data: AgentDto,
    @SerializedName("status")
    val status: Int
)
