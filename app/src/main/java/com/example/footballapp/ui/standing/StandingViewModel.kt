package com.example.footballapp.ui.standing

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.data.standing.StandingRepository
import com.example.footballapp.data.standing.remote.response.Standing
import com.example.footballapp.data.standing.remote.response.StandingResponse
import com.example.footballapp.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StandingViewModel @Inject constructor(
    val repository: StandingRepository
) : ViewModel() {

    private val _leagueTableLiveData = MutableLiveData<Resource<List<Standing>>>()
    val leagueTableLiveData = _leagueTableLiveData

    fun getLeagueTable(id: String) = viewModelScope.launch {
        safeGetLeagueTable(id)
    }

    private suspend fun safeGetLeagueTable(id: String) {
        val response = repository.getLeagueTable(id)
        _leagueTableLiveData.value = handleGetLeagueTableResponse(response)
    }

    private fun handleGetLeagueTableResponse(response: Response<StandingResponse>): Resource<List<Standing>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val standingResponse = resultResponse.table

                // sort by rank
                val list = standingResponse?.sortedWith(compareBy {
                    it.intRank.toInt()
                })
                return Resource.success(list)
            }
        }
        return Resource.error("Error")
    }
}