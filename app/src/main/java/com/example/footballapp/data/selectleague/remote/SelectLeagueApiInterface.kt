package com.example.footballapp.data.selectleague.remote

import com.example.footballapp.data.league.remote.response.LeagueResponse
import com.example.footballapp.data.selectleague.remote.response.SelectLeagueResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SelectLeagueApiInterface {

    @GET("search_all_leagues.php")
    suspend fun getAllLeague(
        @Query("c")
        country: String,
        @Query("s")
        sports: String
    ): Response<SelectLeagueResponse>

    @GET("lookupleague.php")
    suspend fun getLeagueDetail(
        @Query("id")
        id: String
    ): Response<LeagueResponse>

}