package com.tmt.vuaghepchu.ui.minigame.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tmt.vuaghepchu.ui.minigame.model.GameModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private var apiService: ApiService? = null

    init {
        apiService = RetrofitClient.create()
    }

    private fun <T> makeApiCall(apiCall: Call<T>?): LiveData<T> {
        val data = MutableLiveData<T>()

        apiCall?.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {}
        })

        return data
    }

    fun getSportGame(): LiveData<ArrayList<GameModel>> {
        return makeApiCall(apiService?.getSportData())
    }
}