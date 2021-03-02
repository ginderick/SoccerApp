package com.example.footballapp.ui.team


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.footballapp.base.BaseResponse
import com.example.footballapp.data.team.TeamRepository
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    val teamRepository: TeamRepository
) : ViewModel() {

    val searchTeam = MutableLiveData<Resource<List<Team>>>()
    var searchTeamResponse: List<Team>? = null

    init {
        searchTeam.value = Resource.loading()
    }

    fun searchTeam(query: String) = viewModelScope.launch {
        safeSearchTeam(query)
    }

    private suspend fun safeSearchTeam(query: String) {
        val response = teamRepository.getSearchTeam(query)
        searchTeam.value = handleSearchTeamResponse(response)

    }

    private fun handleSearchTeamResponse(response: Response<BaseResponse>): Resource<List<Team>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val teamResultResponse = resultResponse.teams
                return if (teamResultResponse != null) Resource.success(teamResultResponse) else Resource.error(response.message())
            }
        }
        return Resource.error(response.message())
    }
}