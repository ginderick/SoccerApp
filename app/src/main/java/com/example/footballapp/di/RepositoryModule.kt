package com.example.footballapp.di

import com.example.footballapp.data.team.TeamRepository
import com.example.footballapp.data.team.remote.TeamApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideTeamRepository(
        apiService: TeamApiInterface
    ): TeamRepository {
        return TeamRepository(apiService)
    }
}