package com.example.footballapp.data.match.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MatchResponse(

    @SerializedName("events")
    @Expose
    val events: List<Match>
)