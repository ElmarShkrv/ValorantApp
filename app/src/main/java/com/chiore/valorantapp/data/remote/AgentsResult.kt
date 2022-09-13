package com.chiore.valorantapp.data.remote

data class AgentsResult(
    val `data`: List<Data>,
    val status: Int,
)

data class Data(
    val abilities: List<Ability>,
    val splash: String,
    val assetPath: String,
    val background: String,
    val tiers: List<Tier>,
    val backgroundGradientColors: List<String>,
    val bustPortrait: Any,
    val characterTags: List<String>,
    val description: String,
    val developerName: String,
    val displayIcon: String,
    val displayIconSmall: String,
    val displayName: String,
    val fullPortrait: String,
    val fullPortraitV2: Any,
    val isAvailableForTest: Boolean,
    val isBaseContent: Boolean,
    val isFullPortraitRightFacing: Boolean,
    val isPlayableCharacter: Boolean,
    val killfeedPortrait: String,
    val role: Role,
    val uuid: String,
    val voiceLine: VoiceLine,
)

data class Ability(
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val slot: String,
)

data class Tier(
    val backgroundColor: String,
    val color: String,
    val division: String,
    val divisionName: String,
    val largeIcon: String,
    val rankTriangleDownIcon: String,
    val rankTriangleUpIcon: String,
    val smallIcon: String,
    val tier: Int,
    val tierName: String
)

data class Role(
    val assetPath: String,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val uuid: String,
)

data class VoiceLine(
    val maxDuration: Double,
    val mediaList: List<Media>,
    val minDuration: Double,
)

data class Media(
    val id: Int,
    val wave: String,
    val wwise: String,
)