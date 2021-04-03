package com.example.footballapp.data.standing.remote

import com.example.footballapp.data.standing.remote.response.StandingResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StandingApiInterface {

    @GET("lookuptable.php")
    fun getLeagueTable(
        @Query("l")
        idLeague: String
    ): Call<StandingResponse>
}