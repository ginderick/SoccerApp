package com.example.footballapp.ui.league

import android.app.Application
import androidx.lifecycle.*
import com.example.footballapp.data.league.LeagueRepository
import com.example.footballapp.data.league.remote.response.LeagueResponse
import com.example.footballapp.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LeagueViewModel @Inject constructor(
    val leagueRepository: LeagueRepository
) : ViewModel() {

    val leagueLiveData = MutableLiveData<Resource<LeagueResponse>>()

    init {
        leagueLiveData.value = Resource.loading()
    }
    fun getLeagueDetail(id: String) = viewModelScope.launch {
            safeGetLeagueDetail(id)
    }

    private fun safeGetLeagueDetail(id: String) {
        viewModelScope.launch {
            val response = leagueRepository.getSearchLeague(id)
            leagueLiveData.value = handleSafeGetLeagueDetail(response)
        }
    }

    private fun handleSafeGetLeagueDetail(response: Response<LeagueResponse>): Resource<LeagueResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val teamResultResponse = resultResponse
                return if (teamResultResponse != null) Resource.success(teamResultResponse) else Resource.error(response.message())
            }
        }
        return Resource.error(response.message())
    }

    fun getLeagueList() = leagueRepository.getSavedLeagues()

}











