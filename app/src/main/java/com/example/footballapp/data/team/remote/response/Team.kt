package com.example.footballapp.data.team.remote.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Team(
    @SerializedName("idTeam")
    @Expose
    val idTeam: String? = "",

    @SerializedName("strTeam")
    @Expose
    val strTeam: String? = "",

    @SerializedName("strLeague")
    @Expose
    val strLeague: String? = "",

    @SerializedName("strCountry")
    @Expose
    val strCountry: String? = "",

    @SerializedName("strStadium")
    @Expose
    val strStadium: String? = "",

    @SerializedName("strDescriptionEN")
    @Expose
    val strDescriptionEN: String? = "",

    @SerializedName("intFormedYear")
    @Expose
    val intFormedYear: String? = "",

    @SerializedName("strTeamBadge")
    @Expose
    val strTeamBadge: String? = "",

    @SerializedName("teams")
    @Expose
    val teams: List<Team>


)


