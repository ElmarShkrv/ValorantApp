package com.chiore.valorantapp.repository

import com.chiore.valorantapp.data.remote.ValorantApi
import com.chiore.valorantapp.data.model.agents.AgentResponse
import com.chiore.valorantapp.util.Resource
import javax.inject.Inject

class AgentsRepository @Inject constructor(
    private val valorantApi: ValorantApi,
) {
    suspend fun allAgents(): Resource<AgentResponse> {
        return try {
            val response = valorantApi.getAgents()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                } ?: Resource.Error("Response is null")
            } else {
                Resource.Error("Response is not successful")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}