package com.example.footballapp.ui.team

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.footballapp.data.team.TeamRepositoryImpl
import com.example.footballapp.data.team.remote.response.TeamResponse
import com.example.footballapp.others.Status
import com.example.footballapp.ui.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class TeamViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: TeamViewModel
    private lateinit var repositoryImpl: TeamRepositoryImpl

    @Before
    fun setup() {
        repositoryImpl = mock(TeamRepositoryImpl::class.java)
        viewModel = TeamViewModel(repositoryImpl)
    }

    @Test
    fun `test when view model is created then it should return loading status`() {
        Assert.assertEquals(Status.LOADING, viewModel.searchTeamLiveData.getOrAwaitValueTest().status)
    }

    @Test
    fun `test when searchTeam is not null then it should return success status `() {
        runBlocking {
            `when`(repositoryImpl.getSearchTeam("TeamName")).thenReturn(Response.success(
                TeamResponse(listOf())
            ))
            viewModel.searchTeam("TeamName")
            Assert.assertEquals(Status.SUCCESS, viewModel.searchTeamLiveData.getOrAwaitValueTest().status)
        }
    }

    @Test
    fun `test when searchTeam is null then it should return error status`() {
        runBlocking {

            val response = "{\"teams\": null}".toResponseBody("application/json".toMediaTypeOrNull())
            `when`(repositoryImpl.getSearchTeam("TeamName")).thenReturn(Response.error(400, response))
            viewModel.searchTeam("TeamName")
            Assert.assertEquals(Status.ERROR, viewModel.searchTeamLiveData.getOrAwaitValueTest().status)
        }
    }


}