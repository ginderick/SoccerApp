package com.example.footballapp.data.match

import com.example.footballapp.data.match.remote.MatchApiInterface
import com.example.footballapp.data.match.remote.response.MatchResponse
import retrofit2.Response
import javax.inject.Inject

class MatchRepositoryImpl
@Inject constructor(
    val apiService: MatchApiInterface
) : MatchRepository {

    override suspend fun getMatchEvents(id: String): Response<MatchResponse> {
        return apiService.getMatchEvents(id)
    }


}