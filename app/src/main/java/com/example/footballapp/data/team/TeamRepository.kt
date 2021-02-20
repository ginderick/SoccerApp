package com.example.footballapp.data.team
import com.example.footballapp.data.team.remote.TeamApiInterface
import javax.inject.Inject

class TeamRepository
@Inject constructor(
    val apiService: TeamApiInterface
) {

    suspend fun getSearchTeam(query: String) {
        apiService.getSearchTeam(query)
    }
}