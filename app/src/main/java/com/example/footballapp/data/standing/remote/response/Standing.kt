package com.example.footballapp.data.standing.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Standing(

   @SerializedName("intRank")
   val intRank: String = "",

   @SerializedName("idTeam")
   val idTeam: String = "",

   @SerializedName("strTeam")
   val strTeam: String = "",

   @SerializedName("strLeague")
   val strLeague: String = "",

   @SerializedName("strForm")
   val strForm: String = "",

   @SerializedName("intPlayed")
   val intPlayed: String = "",

   @SerializedName("intWin")
   val intWin: String = "",

   @SerializedName("intLoss")
   val intLoss: String = "",

   @SerializedName("intDraw")
   val intDraw: String = "",

   @SerializedName("intGoalsFor")
   val intGoalsFor: String = "",

   @SerializedName("intGoalsAgainst")
   val intGoalsAgainst: String = "",

   @SerializedName("intGoalDifference")
   val intGoalDifference: String = "",

   @SerializedName("intPoints")
   val intPoints: String = "",
) : Serializable