package com.example.footballapp.ui.team

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.footballapp.data.team.TeamRepository
import com.example.footballapp.data.team.remote.response.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    val teamRepository: TeamRepository,
    application: Application
    ) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    private val _searchTeam = MutableLiveData<List<Team>>().apply { value = emptyList() }
    val searchTeam: LiveData<List<Team>> = _searchTeam


    val text: LiveData<String> = _text


    fun handleSearchTeam(query: String){
        viewModelScope.launch {
            teamRepository.getSearchTeam(query)
        }
    }
}