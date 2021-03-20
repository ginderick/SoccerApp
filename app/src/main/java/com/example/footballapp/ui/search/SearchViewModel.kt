package com.example.footballapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.data.country.remote.response.Country
import com.example.footballapp.data.league.remote.response.League
import com.example.footballapp.data.standing.CountryRepository
import com.example.footballapp.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val countryRepository: CountryRepository
) : ViewModel() {

    private val _countryLiveData = MutableLiveData<Resource<List<Country>>>()
    val countryLiveData = _countryLiveData

    fun getCountry() = viewModelScope.launch {
        countryLiveData.value = countryRepository.getCountry("soccer")
    }


}