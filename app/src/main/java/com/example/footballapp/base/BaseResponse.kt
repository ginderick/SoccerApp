package com.example.footballapp.base

import com.example.footballapp.data.league.remote.response.League
import com.example.footballapp.data.team.remote.response.Team
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse(

    @SerializedName("leagues")
    @Expose
    val teams: List<Team>?,

    @SerializedName("events")
    @Expose
    val league: League?
)