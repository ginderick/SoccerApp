package com.example.footballapp.data.country.remote.response


import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("countries")
    val countries: List<Country>?
)