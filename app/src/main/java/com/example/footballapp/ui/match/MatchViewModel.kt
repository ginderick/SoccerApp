package com.example.footballapp.ui.match

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.data.match.MatchRepository
import com.example.footballapp.data.match.remote.response.Match
import com.example.footballapp.data.match.remote.response.MatchResponse
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*
import javax.inject.Inject


@HiltViewModel
class MatchViewModel @Inject constructor(
    val matchRepository: MatchRepository
) : ViewModel() {

    private val _matchEventsList = MutableLiveData<Resource<List<Match>>>()
    val matchEventList = _matchEventsList

    private val _homeTeamBadgeList = mutableListOf<String>()
    val homeTeamBadgeList = _homeTeamBadgeList


    private fun saveHomeTeam(match: List<Match>) {
        for (item in match) {
            _homeTeamBadgeList.add(item.idHomeTeam)
        }
    }


    fun getMatchEvents(id: String) = viewModelScope.launch {
        safeGetMatchEvents(id)
    }

    private suspend fun safeGetMatchEvents(id: String) {
        val response = matchRepository.getMatchEvents(id)
        _matchEventsList.value = handleGetMatchEventsResponse(response)
        saveHomeTeam(_matchEventsList.value!!.data!!)
        Log.d("MatchViewModel", homeTeamBadgeList.toString())
    }

    private fun handleGetMatchEventsResponse(response: Response<MatchResponse>): Resource<List<Match>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val matchEventsResultResponse = resultResponse.events
                return if (matchEventsResultResponse != null) Resource.success(
                    matchEventsResultResponse
                )
                else Resource.error(response.message())
            }
        }
        return Resource.error(response.message())
    }

}