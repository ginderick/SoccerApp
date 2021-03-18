package com.example.footballapp.data.match.remote

import com.example.footballapp.data.match.remote.response.MatchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchApiInterface {

    @GET("eventspastleague.php")
    suspend fun getMatchEvents(
        @Query("id")
        id: String
    ): Response<MatchResponse>
}