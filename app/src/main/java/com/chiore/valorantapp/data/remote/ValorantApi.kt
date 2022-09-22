package com.chiore.valorantapp.data.remote

import com.chiore.valorantapp.data.model.agents.AgentDetailResponse
import com.chiore.valorantapp.data.model.agents.AgentResponse
import com.chiore.valorantapp.data.model.competitivetiers.CompetitiveTiersResponse
import com.chiore.valorantapp.data.model.maps.MapDetailResponse
import com.chiore.valorantapp.data.model.maps.MapsResponse
import com.chiore.valorantapp.data.model.weapons.WeaponDetailResponse
import com.chiore.valorantapp.data.model.weapons.WeaponsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantApi {

    @GET("v1/agents/?isPlayableCharacter=true")
    suspend fun getAgents(): Response<AgentResponse>

    @GET("v1/agents/{agentUuid}")
    suspend fun getAgentByUuid(
        @Path("agentUuid") agentUuid: String
    ): Response<AgentDetailResponse>

    @GET("v1/maps")
    suspend fun getMaps(): Response<MapsResponse>

    @GET("v1/maps/{mapUuid}")
    suspend fun getMapByUuid(
        @Path("mapUuid") mapUuid: String
    ): Response<MapDetailResponse>

    @GET("v1/weapons")
    suspend fun getWeapons(): Response<WeaponsResponse>

    @GET("v1/weapons/{weaponUuid}")
    suspend fun getWeaponByUuid(
        @Path("weaponUuid") weaponUuid: String
    ): Response<WeaponDetailResponse>

    @GET("v1/competitivetiers")
    suspend fun getTiers(): Response<CompetitiveTiersResponse>
}