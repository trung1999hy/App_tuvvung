package com.tmt.vuaghepchu.ui.minigame.network

import com.tmt.vuaghepchu.ui.minigame.model.GameModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET(Constant.sportUrl)
    fun getSportData() : Call<ArrayList<GameModel>>
}