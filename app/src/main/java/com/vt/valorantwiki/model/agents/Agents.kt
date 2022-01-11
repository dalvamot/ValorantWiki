package com.vt.valorantwiki.model.agents


import com.google.gson.annotations.SerializedName

data class Agents(
    @SerializedName("data")
    val `data`: List<Agent>,
    @SerializedName("status")
    val status: Int
)