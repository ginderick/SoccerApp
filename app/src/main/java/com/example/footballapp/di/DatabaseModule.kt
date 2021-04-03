package com.example.footballapp.di

import android.content.Context
import androidx.room.Room
import com.example.footballapp.data.league.db.LeagueDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesArticleDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        LeagueDatabase::class.java,
        "leagues_db.db"
    ).build()


    @Singleton
    @Provides
    fun provideLeagueDao(db: LeagueDatabase) = db.getLeagueDao()
}