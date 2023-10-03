package com.tmt.vuaghepchu.ui.minigame.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun create(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}