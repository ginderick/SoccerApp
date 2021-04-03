package com.example.footballapp.ui.selectleague

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.data.league.remote.response.League
import com.example.footballapp.data.selectleague.SelectLeagueRepository
import com.example.footballapp.data.selectleague.remote.response.SelectLeague
import com.example.footballapp.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectLeagueViewModel @Inject constructor(
    val repository: SelectLeagueRepository
) : ViewModel() {

    private val _selectLeagueLiveData=  MutableLiveData<Resource<List<SelectLeague>>>()
    val selectLeagueLiveData = _selectLeagueLiveData

    private val _leagueLiveData = MutableLiveData<Resource<League>>()
    val leagueLiveData = _leagueLiveData


    fun getAllLeagues(country: String, sport: String) = viewModelScope.launch {
        val response = repository.getAllLeague(country, sport)
        val a = response.body()?.countrys
        _selectLeagueLiveData.value = Resource.success(a)
    }
}