package com.vt.valorantwiki.utils

import com.vt.valorantwiki.model.agents.Agents

sealed class UIState{
    class LOADING(val isLoading: Boolean = true): UIState()
    class SUCCESS( val success: Agents): UIState()
    class ERROR(val error: Throwable):UIState()
}
