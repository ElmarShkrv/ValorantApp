package com.chiore.valorantapp.data.model.maps

import com.google.gson.annotations.SerializedName

data class MapsResponse(
    @SerializedName("data")
    val data: List<MapDto>,
    @SerializedName("status")
    val status: Int
)
