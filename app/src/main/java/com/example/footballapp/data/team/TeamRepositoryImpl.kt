package com.example.footballapp.data.team

import androidx.lifecycle.map
import com.example.footballapp.base.BaseResponse
import com.example.footballapp.data.team.remote.TeamApiInterface
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.data.team.remote.response.TeamResponse
import com.example.footballapp.others.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TeamRepositoryImpl
@Inject constructor(
    val apiService: TeamApiInterface
) : TeamRepository {

    override suspend fun getSearchTeam(id: String): Response<TeamResponse> {
        return apiService.getSearchTeam(id)
    }

    override suspend fun getTeamList(id: String): Response<TeamResponse> {
        return apiService.getTeamList(id)
    }
}