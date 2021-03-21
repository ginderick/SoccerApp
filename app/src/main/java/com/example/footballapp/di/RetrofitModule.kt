package com.example.footballapp.di

import com.example.footballapp.data.league.remote.LeagueApiInterface
import com.example.footballapp.data.match.remote.MatchApiInterface
import com.example.footballapp.data.selectleague.remote.SelectLeagueApiInterface
import com.example.footballapp.data.standing.remote.CountryApiInterface
import com.example.footballapp.data.standing.remote.StandingApiInterface
import com.example.footballapp.data.team.remote.TeamApiInterface
import com.example.footballapp.others.Constants
import com.example.footballapp.utils.nullOnEmptyConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton



@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideTeamService(retrofit: Retrofit): TeamApiInterface {
        return retrofit.create(TeamApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideLeagueService(retrofit: Retrofit): LeagueApiInterface {
        return retrofit.create(LeagueApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideMatchService(retrofit: Retrofit): MatchApiInterface {
        return retrofit.create(MatchApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideStandingService(retrofit: Retrofit): StandingApiInterface {
        return retrofit.create(StandingApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideCountryService(retrofit: Retrofit): CountryApiInterface {
        return retrofit.create(CountryApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideSelectLeagueService(retrofit: Retrofit): SelectLeagueApiInterface {
        return retrofit.create(SelectLeagueApiInterface::class.java)
    }




}