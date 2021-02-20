package com.example.footballapp.ui.team

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.footballapp.data.team.TeamRepository
import com.example.footballapp.data.team.remote.response.Team
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TeamViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: TeamRepository
    private lateinit var viewModel: TeamViewModel

    private lateinit var _isLoadingLiveData: Observer<Boolean>
    private lateinit var _isErrorLiveData: Observer<Boolean>
    private lateinit var _searchTeam: Observer<List<Team>>

    @Before
    fun setup() {
        repository = mock()
        viewModel = TeamViewModel(repository)

        _isLoadingLiveData = mock()
        _isErrorLiveData = mock()
        _searchTeam = mock()

        viewModel.loadingLiveData.observeForever(_isLoadingLiveData)
        viewModel.errorLiveData.observeForever(_isErrorLiveData)
        viewModel.searchTeam.observeForever(_searchTeam)
    }

    fun testGetSearchTeam() {}

    fun testHandleSearchTeam() {}

    @Test
    fun init_shouldShowLoading() {
        verify(_isLoadingLiveData).onChanged(eq(true))
    }

    @Test
    fun init_shouldShowError() {
        verify(_isErrorLiveData).onChanged(eq(false))
    }

    @Test
    fun init_shouldShowEmptyTeamList() {
        verify(_searchTeam).onChanged(eq(emptyList()))
    }

}