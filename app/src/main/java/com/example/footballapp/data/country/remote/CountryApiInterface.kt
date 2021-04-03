package com.example.footballapp.data.standing.remote


import com.example.footballapp.data.country.remote.response.CountryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryApiInterface {

    @GET("all_countries.php")
    suspend fun getCountry(
        @Query("s")
        category: String
    ): Response<CountryResponse>

}