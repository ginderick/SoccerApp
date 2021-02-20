package com.example.footballapp.ui.team

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.footballapp.data.team.TeamRepository
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    ) : ViewModel() {

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _isLoadingLiveData

    private val _isErrorLiveData = MutableLiveData<Boolean>()
    val errorLiveData: LiveData<Boolean> = _isErrorLiveData

    private val _searchTeam = MutableLiveData<List<Team>>()
    val searchTeam: LiveData<List<Team>> = _searchTeam

    init {
        _isLoadingLiveData.value = true
        _isErrorLiveData.value = false
        _searchTeam.value = emptyList()  //set initial list as empty
    }

    fun handleSearchTeam(query: String){
        viewModelScope.launch {
            teamRepository.getSearchTeam(query)
        }
    }
}