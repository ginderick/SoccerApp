package com.example.footballapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.footballapp.base.BaseResponse
import com.example.footballapp.data.team.TeamRepository
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.others.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockResponse
import org.mockito.Mock
import retrofit2.Response

//class FakeTeamRepositoryImpl : TeamRepository {
//
//    override suspend fun getSearchTeam(query: String): Response<MockResponse> {
//        return null
//    }
//
//}
