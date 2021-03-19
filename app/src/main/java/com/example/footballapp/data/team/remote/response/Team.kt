package com.example.footballapp.data.team.remote.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Team(
    @SerializedName("idTeam")
    val idTeam: String = "",

    @SerializedName("strTeam")
    val strTeam: String? = "",

    @SerializedName("strLeague")
    val strLeague: String? = "",

    @SerializedName("strCountry")
    val strCountry: String? = "",

    @SerializedName("strStadium")
    val strStadium: String? = "",

    @SerializedName("strStadiumThumb")
    val strStadiumThumb: String? = "",

    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String? = "",

    @SerializedName("intFormedYear")
    val intFormedYear: String? = "",

    @SerializedName("strTeamBadge")
    val strTeamBadge: String? = "",

    @SerializedName("teams")
    val teams: List<Team>
) : Serializable


