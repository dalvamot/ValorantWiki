package com.vt.valorantwiki.model.playercards


import com.google.gson.annotations.SerializedName

data class PlayerCards(
    @SerializedName("data")
    val `data`: List<PlayerCard>,
    @SerializedName("status")
    val status: Int
)