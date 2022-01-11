package com.vt.valorantwiki.model.playercards


import com.google.gson.annotations.SerializedName

data class PlayerCard(
    @SerializedName("assetPath")
    val assetPath: String,
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("isHiddenIfNotOwned")
    val isHiddenIfNotOwned: Boolean,
    @SerializedName("largeArt")
    val largeArt: String,
    @SerializedName("smallArt")
    val smallArt: String,
    @SerializedName("themeUuid")
    val themeUuid: Any,
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("wideArt")
    val wideArt: String
)