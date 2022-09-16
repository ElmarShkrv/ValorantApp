package com.chiore.valorantapp.ui.fragments.agentsfragment.agentsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiore.valorantapp.data.model.agents.AgentDto
import com.chiore.valorantapp.repository.AgentsDetailsRepository
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentDetailsViewModel @Inject constructor(
    private val repository: AgentsDetailsRepository
): ViewModel() {

    private val _agentDetails = MutableStateFlow<Resource<AgentDto>>(Resource.Loading())
    val agentDetail: Flow<Resource<AgentDto>> = _agentDetails

    fun agentDetails(agentUuid: String) {
        viewModelScope.launch {
            val response = repository.agentDetails(agentUuid)
            response.data?.let {
                _agentDetails.value = Resource.Success(it.data)
            }
        }
    }

}