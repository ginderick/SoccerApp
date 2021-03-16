package com.example.footballapp.data.team.remote

import com.example.footballapp.base.BaseResponse
import com.example.footballapp.data.team.remote.response.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamApiInterface {

    @GET("lookupteam.php")
    suspend fun getSearchTeam(
        @Query("id")
        id: String
        ): Response<TeamResponse>

    @GET("lookup_all_teams.php")
    suspend fun getTeamList(
        @Query("id")
        id: String
    ): Response<TeamResponse>
}