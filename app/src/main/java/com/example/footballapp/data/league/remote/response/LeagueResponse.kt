package com.example.footballapp.data.league.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class LeagueResponse(
    @SerializedName("leagues")
    @Expose
    val leagues: List<League>
) {
}