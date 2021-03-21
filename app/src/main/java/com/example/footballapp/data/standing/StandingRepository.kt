package com.example.footballapp.data.standing

import androidx.lifecycle.MutableLiveData
import com.example.footballapp.data.standing.remote.response.StandingResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response

interface StandingRepository {

    suspend fun getLeagueTable(id: String): MutableLiveData<Response<StandingResponse>>
}