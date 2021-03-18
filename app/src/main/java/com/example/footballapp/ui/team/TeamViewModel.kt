package com.example.footballapp.ui.team


import android.util.Log
import androidx.lifecycle.*
import com.example.footballapp.data.team.TeamRepository
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.data.team.remote.response.TeamResponse
import com.example.footballapp.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    val teamRepository: TeamRepository
) : ViewModel() {

    private val _searchTeamLiveData = MutableLiveData<Resource<Team>>()
    val searchTeamLiveData = _searchTeamLiveData

    private val _teamListLiveData = MutableLiveData<Resource<List<Team>>>()
    val teamListLiveData = _teamListLiveData

    init {
        _searchTeamLiveData.value = Resource.loading()
    }

    fun searchTeam(query: String) = viewModelScope.launch {
        safeSearchTeam(query)
    }

    private suspend fun safeSearchTeam(query: String) {
        val response = teamRepository.getSearchTeam(query)
        _searchTeamLiveData.value = handleSearchTeamResponse(response)
    }

    private fun handleSearchTeamResponse(response: Response<TeamResponse>): Resource<Team> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val teamResultResponse = resultResponse.teams[0]
                return if (teamResultResponse != null) Resource.success(teamResultResponse) else Resource.error(response.message())
            }
        }
        return Resource.error(response.message())
    }

    fun getTeamList(id: String) = viewModelScope.launch {
        safeGetTeamList(id)
    }

    private suspend fun safeGetTeamList(id: String) {
        val response = teamRepository.getTeamList(id)
        _teamListLiveData.value = handleGetTeamListResponse(response)
    }

    private fun handleGetTeamListResponse(response: Response<TeamResponse>): Resource<List<Team>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                Log.d("TeamViewModel", resultResponse.toString())
                val teamResultResponse = resultResponse.teams
                Log.d("TeamViewModel", resultResponse.teams.toString())
                return if (teamResultResponse != null) Resource.success(teamResultResponse) else Resource.error(response.message())
            }
        }
        return Resource.error(response.message())
    }
}