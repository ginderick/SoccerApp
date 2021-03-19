package com.example.footballapp.ui.standing

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
): ViewModel() {

    private val _leagueTableLiveData = MutableLiveData<Resource<ArrayDeque<Standing>>>()
    val leagueTableLiveData = _leagueTableLiveData

    fun getLeagueTable(id: String) = viewModelScope.launch {
        safeGetLeagueTable(id)
    }

    private suspend fun safeGetLeagueTable(id: String) {
        val response = repository.getLeagueTable(id)
        _leagueTableLiveData.value = handleGetLeagueTableResponse(response)
    }

    private fun handleGetLeagueTableResponse(response: Response<StandingResponse>): Resource<ArrayDeque<Standing>> {
        val stack = ArrayDeque<Standing>()
        if (response.isSuccessful) {
            response.body().let { resultResponse ->
                resultResponse?.table?.forEach {
                    stack.addLast(it)
                }
                return Resource.success(stack)
            }
        }
        return Resource.error(response.message())

    }
}