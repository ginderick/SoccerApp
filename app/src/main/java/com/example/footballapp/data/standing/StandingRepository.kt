package com.example.footballapp.data.standing

import com.example.footballapp.data.standing.remote.response.StandingResponse
import retrofit2.Response

interface StandingRepository {

    suspend fun getLeagueTable(id: String): Response<StandingResponse>
}