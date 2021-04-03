package com.example.footballapp.data.league.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.footballapp.data.league.remote.response.League


@Database(
    entities = [League::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class LeagueDatabase : RoomDatabase() {
    abstract fun getLeagueDao(): LeagueDao
}