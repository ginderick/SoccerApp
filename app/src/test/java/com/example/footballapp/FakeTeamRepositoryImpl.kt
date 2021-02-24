package com.example.footballapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.footballapp.base.BaseResponse
import com.example.footballapp.data.team.TeamRepository
import com.example.footballapp.others.Resource
import retrofit2.Response

class FakeTeamRepositoryImpl: TeamRepository {

    override suspend fun getSearchTeam(query: String): Response<BaseResponse> {
        return Response.success(BaseResponse(listOf()))
    }

}