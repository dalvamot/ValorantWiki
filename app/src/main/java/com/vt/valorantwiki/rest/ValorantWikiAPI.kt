package com.vt.valorantwiki.rest

import com.vt.valorantwiki.model.agents.Agent
import com.vt.valorantwiki.model.agents.Agents
import com.vt.valorantwiki.model.playercards.PlayerCards
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantWikiAPI {

    //coroutines implementation for network call
    @GET(AGENTS)
    suspend fun retrieveAgents(): Response<Agents>

    @GET(PLAYERCARDS)
    suspend fun retrieveCards(): Response<PlayerCards>


    companion object{
        const val BASE_URL = "https://valorant-api.com/v1/"

        private const val AGENTS = "agents"
        private const val PLAYERCARDS = "playercards"
        //TODO add the other calls for the other buttons
    }
}