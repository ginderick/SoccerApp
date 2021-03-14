package com.example.footballapp.data.league

import androidx.lifecycle.LiveData
import com.example.footballapp.base.BaseResponse
import com.example.footballapp.data.league.remote.response.League
import com.example.footballapp.data.league.remote.response.LeagueResponse
import retrofit2.Response

interface LeagueRepository {

    suspend fun getSearchLeague(id: String): Response<LeagueResponse>
    fun getSavedLeagues(): LiveData<List<League>>
    suspend fun upsert(league: League)
    suspend fun delete(league: League)
}