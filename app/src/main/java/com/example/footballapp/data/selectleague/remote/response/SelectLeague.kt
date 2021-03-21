package com.example.footballapp.data.selectleague.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SelectLeague(

    @SerializedName("strLeague")
    val strLeague: String = "",

    @SerializedName("strBadge")
    val strBadge: String = "",

    @SerializedName("idLeague")
    val idLeague: String = ""

): Serializable