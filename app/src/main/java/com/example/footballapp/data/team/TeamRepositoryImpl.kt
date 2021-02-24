package com.example.footballapp.data.team
import com.example.footballapp.base.BaseResponse
import com.example.footballapp.data.team.remote.TeamApiInterface
import retrofit2.Response
import javax.inject.Inject

class TeamRepositoryImpl
@Inject constructor(
    val apiService: TeamApiInterface
): TeamRepository {

    override suspend fun getSearchTeam(query: String): Response<BaseResponse>  {
        return apiService.getSearchTeam(query)
    }

}