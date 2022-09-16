package com.chiore.valorantapp.data.model.weapons

import com.google.gson.annotations.SerializedName

data class GridPosition(
    @SerializedName("column")
    val column: Int,
    @SerializedName("row")
    val row: Int
)