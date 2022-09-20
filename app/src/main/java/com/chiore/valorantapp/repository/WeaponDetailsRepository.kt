package com.chiore.valorantapp.repository

import com.chiore.valorantapp.api.ValorantApi
import com.chiore.valorantapp.data.model.weapons.WeaponDetailResponse
import com.chiore.valorantapp.util.Resource
import javax.inject.Inject

class WeaponDetailsRepository @Inject constructor(
    private val valorantApi: ValorantApi,
) {
    suspend fun weaponDetails(weaponUuid: String): Resource<WeaponDetailResponse> {
        return try {

            val response = valorantApi.getWeaponByUuid(weaponUuid)
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