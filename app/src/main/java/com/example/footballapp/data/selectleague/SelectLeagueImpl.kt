package com.example.footballapp.data.selectleague

import com.example.footballapp.data.league.remote.LeagueApiInterface
import com.example.footballapp.data.league.remote.response.LeagueResponse
import com.example.footballapp.data.selectleague.remote.SelectLeagueApiInterface
import com.example.footballapp.data.selectleague.remote.response.SelectLeagueResponse
import retrofit2.Response
import javax.inject.Inject

class SelectLeagueImpl @Inject constructor(
    val apiService: SelectLeagueApiInterface
) : SelectLeagueRepository {
    override suspend fun getAllLeague(country: String, sport: String): Response<SelectLeagueResponse> {
        return apiService.getAllLeague(country, sport)
    }
}