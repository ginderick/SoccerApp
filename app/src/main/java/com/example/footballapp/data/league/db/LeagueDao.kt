package com.example.footballapp.data.league.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.footballapp.data.league.remote.response.League
import com.example.footballapp.data.league.remote.response.LeagueResponse

@Dao
interface LeagueDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//     fun upsert(league: League)

    @Query("SELECT * FROM leagues")
    fun getAllLeagues(): LiveData<List<League>>

//    @Delete
//     fun deleteArticle(league: League)

}