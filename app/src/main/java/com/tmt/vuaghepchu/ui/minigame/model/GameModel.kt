package com.tmt.vuaghepchu.ui.minigame.model

import com.google.gson.annotations.SerializedName

data class GameModel(
    @SerializedName("cid")
    var cid: Int,
    @SerializedName("link_game")
    var linkGame: String,
    @SerializedName("logo_game")
    var logoGame: String
)