package com.chiore.valorantapp.ui.fragments.weaponsfragment.weaponsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiore.valorantapp.data.model.weapons.WeaponDto
import com.chiore.valorantapp.repository.WeaponDetailsRepository
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponDetailsViewModel @Inject constructor(
    private val repository: WeaponDetailsRepository
): ViewModel() {

    private val _weaponDetails = MutableStateFlow<Resource<WeaponDto>>(Resource.Loading())
    val weaponDetails: Flow<Resource<WeaponDto>> = _weaponDetails

    fun weaponDetails(weaponUuid: String) {
        viewModelScope.launch {
            val response = repository.weaponDetails(weaponUuid)
            response.data?.let {
                _weaponDetails.value = Resource.Success(it.data)
            }
        }
    }

}