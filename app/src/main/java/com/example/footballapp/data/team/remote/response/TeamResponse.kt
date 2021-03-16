package com.example.footballapp.data.team.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TeamResponse(

    @SerializedName("teams")
    @Expose
    val teams: List<Team>
)