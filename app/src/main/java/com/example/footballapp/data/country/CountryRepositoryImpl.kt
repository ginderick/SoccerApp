package com.example.footballapp.data.standing

import androidx.lifecycle.MutableLiveData
import com.example.footballapp.data.country.remote.response.Country
import com.example.footballapp.data.country.remote.response.CountryResponse
import com.example.footballapp.data.standing.remote.CountryApiInterface
import com.example.footballapp.others.Resource
import retrofit2.Response

class CountryRepositoryImpl(
    val apiService: CountryApiInterface
) : CountryRepository {


    override suspend fun getCountry(sport: String): Resource<List<Country>> {
        val response = apiService.getCountry("sport")

        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val countryResultResponse = resultResponse.countries
                return Resource.success(countryResultResponse)
            }
        }
        return Resource.error(response.message())
    }
}