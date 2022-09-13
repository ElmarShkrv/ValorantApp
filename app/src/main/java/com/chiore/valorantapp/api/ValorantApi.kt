package com.chiore.valorantapp.api

import com.chiore.valorantapp.data.remote.AgentsResult
import retrofit2.Response
import retrofit2.http.GET

interface ValorantApi {

    @GET("v1/agents/?isPlayableCharacter=true")
    suspend fun getAgents(): Response<AgentsResult>

    @GET("v1/maps")
    suspend fun getMaps(): Response<AgentsResult>

    @GET("v1/weapons")
    suspend fun getWeapons(): Response<AgentsResult>

    @GET("v1/competitivetiers")
    suspend fun getTiers(): Response<AgentsResult>
}