package com.chiore.valorantapp.domain.model

import com.chiore.valorantapp.data.model.weapons.Skin
import com.chiore.valorantapp.data.model.weapons.WeaponStats

data class Weapon(
    val category: String,
    val displayIcon: String?,
    val displayName: String,
    var skins: List<Skin>,
    val uuid: String,
    val weaponStats: WeaponStats?
)
