package com.example.footballapp.data.team.remote

import com.example.footballapp.base.BaseResponse
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.others.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamApiInterface {

    @GET("searchteams.php")
    suspend fun getSearchTeam(
        @Query("t")
        id: String
    ): Response<BaseResponse>
}