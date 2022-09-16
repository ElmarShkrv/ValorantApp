package com.chiore.valorantapp.ui.fragments.agentsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiore.valorantapp.data.model.agents.AgentDto
import com.chiore.valorantapp.repository.AgentsRepository
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val repository: AgentsRepository
): ViewModel() {

    private val _allAgents = MutableStateFlow<Resource<List<AgentDto>>>(Resource.Loading())
    val allAgents: Flow<Resource<List<AgentDto>>> = _allAgents

    fun allAgents() {
        viewModelScope.launch {
            val response = repository.allAgents()
            response.data?.let {
                _allAgents.value = Resource.Success(it.data)
            }
        }
    }

    private val _state = MutableStateFlow(AgentsActivityState())
    val state: StateFlow<AgentsActivityState> get() = _state

    fun setLayoutManager() {
        when (this.getListType()) {
            ListType.GridLayout -> this.setListLayoutManager(ListType.LinearLayout)
            else -> this.setListLayoutManager(ListType.GridLayout)
        }
    }

    fun getListType(): ListType {
        return _state.value.listType
    }

    private fun setListLayoutManager(newType: ListType) {
        _state.value = _state.value.copy(
            listType = newType
        )
    }

}