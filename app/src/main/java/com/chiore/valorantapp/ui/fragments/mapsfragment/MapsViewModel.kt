package com.chiore.valorantapp.ui.fragments.mapsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiore.valorantapp.data.remote.Data
import com.chiore.valorantapp.repository.MapsRepository
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: MapsRepository
): ViewModel() {

    private val _allMaps = MutableStateFlow<Resource<List<Data>>>(Resource.Loading())
    val allMaps: Flow<Resource<List<Data>>> = _allMaps

    fun allMaps() {
        viewModelScope.launch {
            val response = repository.allMaps()
            response.data?.let {
                _allMaps.value = Resource.Success(it.data)
            }
        }
    }

}