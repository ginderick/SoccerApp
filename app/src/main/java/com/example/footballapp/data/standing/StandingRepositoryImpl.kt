package com.example.footballapp.data.standing

import android.util.Log
import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.footballapp.data.standing.remote.StandingApiInterface
import com.example.footballapp.data.standing.remote.response.StandingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StandingRepositoryImpl(
    val apiService: StandingApiInterface
) : StandingRepository {

    override suspend fun getLeagueTable(id: String): MutableLiveData<Response<StandingResponse>> {

        val responseObject = MutableLiveData<Response<StandingResponse>>()
        apiService.getLeagueTable(id).enqueue(object : Callback<StandingResponse> {
            override fun onResponse(
                call: Call<StandingResponse>,
                response: Response<StandingResponse>
            ) {
                    responseObject.value = response
                    Log.d("retrofit", "" + responseObject.value.toString())

            }

            override fun onFailure(call: Call<StandingResponse>, t: Throwable) {
                Log.d("retrofit", "Error" + t.message.toString())

            }
        })

        return responseObject
    }
}