package com.example.footballapp.data.selectleague

import com.example.footballapp.data.league.remote.response.LeagueResponse
import com.example.footballapp.data.selectleague.remote.response.SelectLeagueResponse
import com.example.footballapp.data.team.remote.response.TeamResponse
import retrofit2.Response

interface SelectLeagueRepository {

    suspend fun getAllLeague(country: String, sport: String): Response<SelectLeagueResponse>

}