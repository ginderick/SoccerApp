package com.example.footballapp.data.match

import com.example.footballapp.data.match.remote.response.MatchResponse
import retrofit2.Response

interface MatchRepository {

    suspend fun getMatchEvents(id: String): Response<MatchResponse>
}