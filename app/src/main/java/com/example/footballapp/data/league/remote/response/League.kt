package com.example.footballapp.data.league.remote.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.footballapp.data.league.db.Source
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "leagues"
)
data class League(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @SerializedName("idLeague")
    @Expose
    val idLeague: String = "",

    @SerializedName("strCountry")
    @Expose
    val strCountry: String = "",

    @SerializedName("strLeague")
    @Expose
    val strLeague: String = "",

    @SerializedName("strDescriptionEN")
    @Expose
    val strDescriptionEN: String = "",

    @SerializedName("strBadge")
    @Expose
    val strBadge: String = "",

    @SerializedName("strLogo")
    @Expose
    val strLogo: String = "",
    
): Serializable