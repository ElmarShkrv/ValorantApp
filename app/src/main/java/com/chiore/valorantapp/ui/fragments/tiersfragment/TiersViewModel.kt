package com.chiore.valorantapp.ui.fragments.tiersfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiore.valorantapp.data.remote.Data
import com.chiore.valorantapp.data.remote.Tier
import com.chiore.valorantapp.repository.TiersRepository
import com.chiore.valorantapp.repository.WeaponsRepository
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TiersViewModel @Inject constructor(
    private val repository: TiersRepository
): ViewModel() {

    private val _allTiers = MutableStateFlow<Resource<List<Data>>>(Resource.Loading())
    val allTiers: Flow<Resource<List<Data>>> = _allTiers

    fun allTiers() {
        viewModelScope.launch {
            val response = repository.allTiers()
            response.data?.let {
                _allTiers.value = Resource.Success(it.data)
            }
        }
    }

}