package com.chiore.valorantapp.data.model.competitivetiers

import com.google.gson.annotations.SerializedName

data class TierDto(
    @SerializedName("backgroundColor")
    val backgroundColor: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("division")
    val division: String,
    @SerializedName("divisionName")
    val divisionName: String,
    @SerializedName("largeIcon")
    val largeIcon: String,
    @SerializedName("rankTriangleDownIcon")
    val rankTriangleDownIcon: Any,
    @SerializedName("rankTriangleUpIcon")
    val rankTriangleUpIcon: Any,
    @SerializedName("smallIcon")
    val smallIcon: String,
    @SerializedName("tier")
    val tier: Int,
    @SerializedName("tierName")
    val tierName: String
)
