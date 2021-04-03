package com.example.footballapp.ui.general

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel: ViewModel() {

    private val _leagueId = MutableLiveData<String>()
    val leagueId: LiveData<String> = _leagueId

    fun setLeagueId(id: String) {
        _leagueId.value = id
    }


}