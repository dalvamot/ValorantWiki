package com.vt.valorantwiki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vt.valorantwiki.rest.AgentsRepository
import com.vt.valorantwiki.utils.UIState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class AgentsViewModel(
    private val agentsRepository: AgentsRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher)
) : CoroutineScope by coroutineScope, ViewModel(){

    private val _agentLiveData: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val agentsLiveData: LiveData<UIState> get() = _agentLiveData

    fun subscribeToAgentsInfo(){
        _agentLiveData.postValue(UIState.LOADING())
        collectAgentInfo()
        launch {
            agentsRepository.getAgentsList()
        }
    }

    private fun collectAgentInfo(){
        launch {
            agentsRepository.agentList.collect { uiState ->
                when(uiState){
                    is UIState.LOADING -> {_agentLiveData.postValue(uiState)}
                    is UIState.SUCCESS -> {_agentLiveData.postValue(uiState)}
                    is UIState.ERROR -> {_agentLiveData.postValue(uiState)}
                }
            }
        }
    }
}