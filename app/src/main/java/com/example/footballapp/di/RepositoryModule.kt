package com.example.footballapp.di

import com.example.footballapp.data.league.LeagueRepository
import com.example.footballapp.data.league.LeagueRepositoryImpl
import com.example.footballapp.data.league.db.LeagueDatabase
import com.example.footballapp.data.league.remote.LeagueApiInterface
import com.example.footballapp.data.match.MatchRepository
import com.example.footballapp.data.match.MatchRepositoryImpl
import com.example.footballapp.data.match.remote.MatchApiInterface
import com.example.footballapp.data.standing.CountryRepository
import com.example.footballapp.data.standing.CountryRepositoryImpl
import com.example.footballapp.data.standing.StandingRepository
import com.example.footballapp.data.standing.StandingRepositoryImpl
import com.example.footballapp.data.standing.remote.CountryApiInterface
import com.example.footballapp.data.standing.remote.StandingApiInterface
import com.example.footballapp.data.team.TeamRepositoryImpl
import com.example.footballapp.data.team.TeamRepository
import com.example.footballapp.data.team.remote.TeamApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideTeamRepository(
        apiService: TeamApiInterface
    ) = TeamRepositoryImpl(apiService) as TeamRepository

    @Provides
    @ViewModelScoped
    fun provideLeagueRepository(
        apiService: LeagueApiInterface,
        leagueDatabase: LeagueDatabase
    ) = LeagueRepositoryImpl(apiService, leagueDatabase) as LeagueRepository

    @Provides
    @ViewModelScoped
    fun provideMatchRepository(
        apiService: MatchApiInterface
    ) = MatchRepositoryImpl(apiService) as MatchRepository

    @Provides
    @ViewModelScoped
    fun provideStandingRepository(
        apiService: StandingApiInterface
    ) = StandingRepositoryImpl(apiService) as StandingRepository

    @Provides
    @ViewModelScoped
    fun provideCountryRepository(
        apiService: CountryApiInterface
    ) = CountryRepositoryImpl(apiService) as CountryRepository


}