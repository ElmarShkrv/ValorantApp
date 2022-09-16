package com.chiore.valorantapp.ui.fragments.mapsfragment.mapsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiore.valorantapp.data.model.maps.MapDto
import com.chiore.valorantapp.repository.MapsDetailsRepository
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapDetailsViewModel @Inject constructor(
    private val repository: MapsDetailsRepository,
) : ViewModel() {

    private val _mapDetails = MutableStateFlow<Resource<MapDto>>(Resource.Loading())
    val mapDetails: Flow<Resource<MapDto>> = _mapDetails

    fun mapDetails(mapUuid: String) {
//        runBlocking {
//            _mapDetails.emit(Resource.Loading())
//        }
        viewModelScope.launch {
            val response = repository.mapDetails(mapUuid)
            response.data?.let {
//                _mapDetails.emit(Resource.Success(it.data))
                _mapDetails.value = Resource.Success(it.data)
            }
        }
    }

}