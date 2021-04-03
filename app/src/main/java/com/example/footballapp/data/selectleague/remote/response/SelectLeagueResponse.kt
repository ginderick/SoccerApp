package com.example.footballapp.data.selectleague.remote.response

import com.example.footballapp.data.match.remote.response.Match
import com.google.gson.annotations.SerializedName

data class SelectLeagueResponse(

    @SerializedName("countrys")
    val countrys: List<SelectLeague>
)