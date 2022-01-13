package com.vt.valorantwiki.rest

import com.vt.valorantwiki.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface AgentsRepository{
    val agentList: StateFlow<UIState>
    val playerCardList: StateFlow<UIState>
    suspend fun getAgentsList()
    suspend fun getPlayerCardList()
}

class AgentsRepositoryImpl(
    private val ValorantWikiAPI: ValorantWikiAPI
) : AgentsRepository {
    private val _agentListFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())
    private val _playerCardListFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())

    override val agentList: StateFlow<UIState>
        get() = _agentListFlow
    override val playerCardList: StateFlow<UIState>
        get() = _playerCardListFlow

    override suspend fun getAgentsList() {
        try {
            val response = ValorantWikiAPI.retrieveAgents()

            if(response.isSuccessful){
                response.body()?.let {
                    _agentListFlow.value = UIState.SUCCESSAGENTS(it)
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

    override suspend fun getPlayerCardList() {
        try {
            val response = ValorantWikiAPI.retrieveCards()

            if(response.isSuccessful){
                response.body()?.let {
                    _playerCardListFlow.value = UIState.SUCCESSCARDS(it)
                } ?: run {
                    _playerCardListFlow.value = UIState.ERROR(IllegalStateException("PlayerCards are coming as null!"))
                }
            }else {
                _playerCardListFlow.value = UIState.ERROR(java.lang.Exception(response.errorBody()?.string()))
            }
        }catch (e: java.lang.Exception){

        }
    }
}