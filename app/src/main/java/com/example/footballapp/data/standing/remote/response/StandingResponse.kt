package com.example.footballapp.data.standing.remote.response

import com.google.gson.annotations.SerializedName

data class StandingResponse(
    @SerializedName("table")
    val table: List<Standing>?
)