package com.chiore.valorantapp.data.model.weapons

import com.google.gson.annotations.SerializedName

data class WeaponDetailResponse(
    @SerializedName("data")
    val data: WeaponDto,
    @SerializedName("status")
    val status: Int
)
