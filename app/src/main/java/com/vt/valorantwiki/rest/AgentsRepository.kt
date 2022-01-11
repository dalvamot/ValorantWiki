package com.vt.valorantwiki.rest

import com.vt.valorantwiki.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface AgentsRepository{
    val agentList: StateFlow<UIState>
    suspend fun getAgentsList()
}

class AgentsRepositoryImpl(
    private val ValorantWikiAPI: ValorantWikiAPI
) : AgentsRepository {
    private val _agentListFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())

    override val agentList: StateFlow<UIState>
        get() = _agentListFlow

    override suspend fun getAgentsList() {
        try {

            val response = ValorantWikiAPI.retrieveAgents()

            if(response.isSuccessful){
                response.body()?.let {
                    _agentListFlow.value = UIState.SUCCESS(it)
                } ?: run {
                    _agentListFlow.value = UIState.ERROR(IllegalStateException("Agents are coming as null!"))
                }
            }else {
                _agentListFlow.value = UIState.ERROR(java.lang.Exception(response.errorBody()?.string()))
            }

        }catch (e: Exception){
            _agentListFlow.value = UIState.ERROR(e)
        }
    }
}