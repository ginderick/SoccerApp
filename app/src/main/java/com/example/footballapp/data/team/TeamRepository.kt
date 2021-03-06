package com.example.footballapp.data.team


import com.example.footballapp.base.BaseResponse
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.data.team.remote.response.TeamResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface TeamRepository {

    suspend fun getSearchTeam(query: String): Response<TeamResponse>
}