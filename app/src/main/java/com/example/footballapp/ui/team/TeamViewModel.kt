package com.example.footballapp.ui.team


import androidx.lifecycle.*
import com.example.footballapp.base.BaseResponse
import com.example.footballapp.data.team.TeamRepository
import com.example.footballapp.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    val teamRepository: TeamRepository
) : ViewModel() {


    private var searchTeamResponse: BaseResponse? = null

    private val _searchTeam = MutableLiveData<Resource<BaseResponse>>()
    val searchTeam: LiveData<Resource<BaseResponse>> = _searchTeam


    init {
        _searchTeam.value = Resource.loading()
    }

    fun loadSearchTeam(query: String) = viewModelScope.launch {
        safeSearchTeam(query)
    }

    suspend fun safeSearchTeam(query: String) {
        _searchTeam.value = Resource.loading()
        val response = teamRepository.getSearchTeam(query)
        _searchTeam.value = handleSearchTeamResponse(response)
    }

    private fun handleSearchTeamResponse(response: Response<BaseResponse>): Resource<BaseResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                searchTeamResponse = resultResponse
                return Resource.success(searchTeamResponse ?: resultResponse)
            }
        }
        return Resource.error(response.message())
    }

}