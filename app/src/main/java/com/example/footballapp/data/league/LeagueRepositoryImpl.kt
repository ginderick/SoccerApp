package com.example.footballapp.data.league

import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.footballapp.base.BaseResponse
import com.example.footballapp.data.league.db.LeagueDao
import com.example.footballapp.data.league.db.LeagueDatabase
import com.example.footballapp.data.league.remote.LeagueApiInterface
import com.example.footballapp.data.league.remote.response.League
import com.example.footballapp.data.league.remote.response.LeagueResponse
import com.example.footballapp.data.team.remote.TeamApiInterface
import retrofit2.Response
import javax.inject.Inject

class LeagueRepositoryImpl @Inject constructor(
    val apiService: LeagueApiInterface,
    val leagueDatabase: LeagueDatabase
): LeagueRepository {


    override suspend fun getSearchLeague(id: String): Response<LeagueResponse> {
        return apiService.getLeagueDetail(id)
    }

    override fun getSavedLeagues(): LiveData<List<League>> {
        return leagueDatabase.getLeagueDao().getAllLeagues()
    }

    override suspend fun upsert(league: League) {
        return leagueDatabase.getLeagueDao().upsertLeague(league)
    }

    override suspend fun delete(league: League) {
        return leagueDatabase.getLeagueDao().deleteLeague(league)
    }

}