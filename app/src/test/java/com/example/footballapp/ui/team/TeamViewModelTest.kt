package com.example.footballapp.ui.team

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.footballapp.base.BaseResponse
import com.example.footballapp.data.team.TeamRepositoryImpl
import com.example.footballapp.data.team.remote.TeamApiInterface
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.others.Resource
import com.example.footballapp.others.Status
import com.example.footballapp.ui.MockResponseFileReader
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.net.HttpURLConnection


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
        Assert.assertEquals(Status.LOADING, viewModel.searchTeam.getOrAwaitValueTest().status)
    }

    @Test
    fun `test when searchTeam is not null then it should return success status `() {
        runBlocking {
            `when`(repositoryImpl.getSearchTeam("TeamName")).thenReturn(Response.success(
                BaseResponse(listOf())
            ))
            viewModel.searchTeam("TeamName")
            Assert.assertEquals(Status.SUCCESS, viewModel.searchTeam.getOrAwaitValueTest().status)
        }
    }

    @Test
    fun `test when searchTeam is null then it should return error status`() {
        runBlocking {

            val response = "{\"teams\": null}".toResponseBody("application/json".toMediaTypeOrNull())
            `when`(repositoryImpl.getSearchTeam("TeamName")).thenReturn(Response.error(400, response))
            viewModel.searchTeam("TeamName")
            Assert.assertEquals(Status.ERROR, viewModel.searchTeam.getOrAwaitValueTest().status)
        }

    }


}