package com.chiore.valorantapp.repository

import com.chiore.valorantapp.api.ValorantApi
import com.chiore.valorantapp.data.remote.AgentsResult
import com.chiore.valorantapp.util.Resource
import javax.inject.Inject


class MapsRepository @Inject constructor(
    private val valorantApi: ValorantApi,
) {
    suspend fun allMaps(): Resource<AgentsResult> {
        return try {
            val response = valorantApi.getMaps()
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