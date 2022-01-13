package com.vt.valorantwiki.utils

import com.vt.valorantwiki.model.agents.Agents
import com.vt.valorantwiki.model.playercards.PlayerCards

sealed class UIState{
    class LOADING(val isLoading: Boolean = true): UIState()
    class SUCCESSAGENTS( val agents: Agents): UIState()
    class SUCCESSCARDS( val playercards: PlayerCards): UIState()
    class ERROR(val error: Throwable):UIState()
}
