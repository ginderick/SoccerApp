package com.example.footballapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.data.league.LeagueRepository
import com.example.footballapp.data.league.remote.response.League
import com.example.footballapp.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    val leagueRepository: LeagueRepository
) : ViewModel()  {

    val leagueLiveData = MutableLiveData<Resource<List<League>>>()

    fun getSavedLeague() = leagueRepository.getSavedLeagues()

    fun deleteLeague(league: League) = viewModelScope.launch {
        leagueRepository.delete(league)
    }

}