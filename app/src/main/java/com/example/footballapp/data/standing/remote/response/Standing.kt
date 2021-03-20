package com.example.footballapp.data.standing.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Standing(

   @SerializedName("intRank")
   @Expose
   val intRank: String = "",

   @SerializedName("idTeam")
   @Expose
   val idTeam: String = "",

   @SerializedName("strTeam")
   @Expose
   val strTeam: String = "",

   @SerializedName("strLeague")
   @Expose
   val strLeague: String = "",

   @SerializedName("strForm")
   @Expose
   val strForm: String = "",

   @SerializedName("intPlayed")
   @Expose
   val intPlayed: String = "",

   @SerializedName("intWin")
   @Expose
   val intWin: String = "",

   @SerializedName("intLoss")
   @Expose
   val intLoss: String = "",

   @SerializedName("intDraw")
   @Expose
   val intDraw: String = "",

   @SerializedName("intGoalsFor")
   @Expose
   val intGoalsFor: String = "",

   @SerializedName("intGoalsAgainst")
   @Expose
   val intGoalsAgainst: String = "",

   @SerializedName("intGoalDifference")
   @Expose
   val intGoalDifference: String = "",

   @SerializedName("intPoints")
   @Expose
   val intPoints: String = "",

   @SerializedName("strTeamBadge")
   @Expose
   val strTeamBadge: String = ""

) : Serializable