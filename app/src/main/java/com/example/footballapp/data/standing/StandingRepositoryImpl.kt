package com.example.footballapp.data.standing

import com.example.footballapp.data.standing.remote.StandingApiInterface
import com.example.footballapp.data.standing.remote.response.StandingResponse
import retrofit2.Response

class StandingRepositoryImpl(
    val apiService: StandingApiInterface
): StandingRepository {

    override suspend fun getLeagueTable(id: String): Response<StandingResponse> {
        return apiService.getLeagueTable(id)
    }
}