package com.example.footballapp.data.league.remote

import com.example.footballapp.data.league.remote.response.LeagueResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LeagueApiInterface {

    @GET("lookupleague.php")
    suspend fun getLeagueDetail(
        @Query("id")
        id: String
    ): Response<LeagueResponse>
}