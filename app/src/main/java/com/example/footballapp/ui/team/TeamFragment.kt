package com.example.footballapp.ui.team

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapp.databinding.FragmentTeamBinding
import com.example.footballapp.others.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_team.*

@AndroidEntryPoint
class TeamFragment : Fragment() {

    private val teamViewModel: TeamViewModel by viewModels()
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var leagueId: String
    private lateinit var teamId: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val root = inflater.inflate(R.layout.fragment_team, container, false)
//        return root

        val binding = FragmentTeamBinding.inflate(inflater, container, false)
        context ?: return binding.root

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSharedLeagueIdPref()

        teamViewModel.getTeamList(leagueId)
        teamViewModel.teamListLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    progressBarInTeam.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    teamAdapter.differ.submitList(it.data)

                    progressBarInTeam.visibility = View.GONE
                }

                Status.ERROR -> {
                    progressBarInTeam.visibility = View.GONE
                    Toast.makeText(activity, "An error occurred", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun setupSharedLeagueIdPref() {
        sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        leagueId = sharedPreferences.getString("league", "4328")!!
    }

    private fun setupRecyclerView() {
        teamAdapter = TeamAdapter()
        rvTeamFragment.apply {
            adapter = teamAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}