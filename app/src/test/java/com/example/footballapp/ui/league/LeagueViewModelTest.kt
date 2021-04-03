package com.example.footballapp.ui.league

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.footballapp.data.league.LeagueRepositoryImpl
import com.example.footballapp.data.league.remote.response.League
import com.example.footballapp.data.league.remote.response.LeagueResponse
import com.example.footballapp.others.Status
import com.example.footballapp.ui.MainCoroutineRule
import com.example.footballapp.ui.team.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class LeagueViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: LeagueViewModel
    private lateinit var repositoryImpl: LeagueRepositoryImpl

    @Before
    fun setup() {
        repositoryImpl = Mockito.mock(LeagueRepositoryImpl::class.java)
        viewModel = LeagueViewModel(repositoryImpl)
    }

    @Test
    fun `test when viewmodel is initialized then it should return loading status` () {
        Assert.assertEquals(Status.LOADING, viewModel.leagueLiveData.getOrAwaitValueTest().status)
    }

    @Test
    fun `test when getLeagueDetail is not null then it should return success status `() {
        runBlocking {
            Mockito.`when`(repositoryImpl.getSearchLeague("League")).thenReturn(
                Response.success(
                LeagueResponse(listOf())
            ))
            viewModel.getLeagueDetail("Team")
            Assert.assertEquals(Status.SUCCESS, viewModel.leagueLiveData.getOrAwaitValueTest().status)
        }
    }

    @Test
    fun `test when getLeagueDetail is null then it should return error status`() {
        runBlocking {

            val response = "{\"teams\": null}".toResponseBody("application/json".toMediaTypeOrNull())
            Mockito.`when`(repositoryImpl.getSearchLeague("League"))
                .thenReturn(Response.error(400, response))
            viewModel.getLeagueDetail("Team")
            Assert.assertEquals(Status.ERROR, viewModel.leagueLiveData.getOrAwaitValueTest().status)
        }
    }


}