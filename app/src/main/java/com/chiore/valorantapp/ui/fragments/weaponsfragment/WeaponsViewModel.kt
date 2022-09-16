package com.chiore.valorantapp.ui.fragments.weaponsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiore.valorantapp.data.model.weapons.WeaponDto
import com.chiore.valorantapp.repository.WeaponsRepository
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponsViewModel @Inject constructor(
    private val repository: WeaponsRepository
): ViewModel() {

    private val _allWeapons = MutableStateFlow<Resource<List<WeaponDto>>>(Resource.Loading())
    val allWeapons: Flow<Resource<List<WeaponDto>>> = _allWeapons

    fun allWeapons() {
        viewModelScope.launch {
            val response = repository.allWeapons()
            response.data?.let {
                _allWeapons.value = Resource.Success(it.data)
            }
        }
    }

}