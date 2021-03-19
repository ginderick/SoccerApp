package com.example.footballapp.data.team.remote.response

import com.google.gson.annotations.SerializedName

data class TeamResponse(

    @SerializedName("teams")
    val teams: List<Team>
)