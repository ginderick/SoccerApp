package com.example.footballapp.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.footballapp.R
import com.example.footballapp.data.team.TeamRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TeamFragment : Fragment() {

    private val teamViewModel: TeamViewModel by viewModels()
//    private lateinit var teamViewModelFactory: TeamViewModelFactory

    @Inject
    lateinit var teamRepository: TeamRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        teamViewModelFactory = TeamViewModelFactory(teamRepository)
//        teamViewModel = ViewModelProvider(this, teamViewModelFactory).get(TeamViewModel::class.java)

        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        teamViewModel.handleSearchTeam("Arsenal")
    }
}