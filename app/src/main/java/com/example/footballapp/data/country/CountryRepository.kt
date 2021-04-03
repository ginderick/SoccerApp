package com.example.footballapp.data.standing

import androidx.lifecycle.MutableLiveData
import com.example.footballapp.data.country.remote.response.Country
import com.example.footballapp.data.country.remote.response.CountryResponse
import com.example.footballapp.others.Resource
import retrofit2.Response

interface CountryRepository {

    suspend fun getCountry(sport: String): Resource<List<Country>>
}