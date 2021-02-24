package com.example.footballapp.ui.team

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.footballapp.FakeTeamRepositoryImpl
import com.example.footballapp.others.Resource
import com.example.footballapp.others.Status


import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock



@ExperimentalCoroutinesApi
class TeamViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repositoryImpl: FakeTeamRepositoryImpl
    private lateinit var viewModel: TeamViewModel

    @Before
    fun setup() {
        repositoryImpl = mock(FakeTeamRepositoryImpl::class.java)
        viewModel = TeamViewModel(FakeTeamRepositoryImpl())
    }

    @Test
    fun `test init should show loading`() {
        val value = viewModel.searchTeam.getOrAwaitValueTest()
        Assert.assertEquals(Status.LOADING, value.status)
    }

    @Test
    fun `test loadSearchTeam should return status success`() {
        viewModel.loadSearchTeam("Arsenal")
        val value = viewModel.searchTeam.getOrAwaitValueTest()
        Assert.assertEquals(Status.SUCCESS, value.status)
    }
}