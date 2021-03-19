package com.example.footballapp.data.team

import com.example.footballapp.data.team.remote.response.TeamResponse
import retrofit2.Response


interface TeamRepository {

    suspend fun getSearchTeam(id: String): Response<TeamResponse>
    suspend fun getTeamList(id: String): Response<TeamResponse>
}