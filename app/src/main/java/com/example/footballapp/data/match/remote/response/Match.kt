package com.example.footballapp.data.match.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Match(
    @SerializedName("idEvent")
    @Expose
    val idEvent: String = "",

    @SerializedName("strHomeTeam")
    val strHomeTeam: String = "",

    @SerializedName("strAwayTeam")
    val strAwayTeam: String = "",

    @SerializedName("intHomeScore")
    val intHomeScore: String = "",

    @SerializedName("intAwayScore")
    val intAwayScore: String = "",

    @SerializedName("dateEvent")
    val dateEvent: String = "",

    @SerializedName("idHomeTeam")
    val idHomeTeam: String = "",

    @SerializedName("idAwayTeam")
    val idAwayTeam: String = "",

    @SerializedName("strThumb")
    val strThumb: String = "",

): Serializable