package com.example.footballapp.data.team

import com.example.footballapp.base.BaseResponse
import com.example.footballapp.others.Resource
import retrofit2.Response

interface TeamRepository {

    suspend fun getSearchTeam(query: String): Response<BaseResponse>
}