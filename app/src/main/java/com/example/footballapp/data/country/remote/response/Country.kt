package com.example.footballapp.data.country.remote.response
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Country(

   @SerializedName("name_en")
   @Expose
   val countries: String = "",

) : Serializable