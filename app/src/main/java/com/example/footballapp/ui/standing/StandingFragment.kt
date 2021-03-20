package com.example.footballapp.ui.standing

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapp.R
import com.example.footballapp.data.standing.remote.response.Standing
import com.example.footballapp.databinding.FragmentStandingBinding
import com.example.footballapp.databinding.FragmentTeamBinding
import com.example.footballapp.others.Status
import com.example.footballapp.ui.general.SharedViewModel
import com.example.footballapp.ui.team.TeamAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_standing.*
import kotlinx.android.synthetic.main.fragment_team.*

@AndroidEntryPoint
class StandingFragment : Fragment() {

    private val standingViewModel: StandingViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var standingAdapter: StandingAdapter
    private lateinit var leagueId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStandingBinding.inflate(inflater, container, false)
        context ?: return binding.root

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        sharedViewModel.leagueId.observe(viewLifecycleOwner, {
            leagueId = "4330"
            standingViewModel.getLeagueTable(leagueId)
        })


        standingViewModel.leagueTableLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    progressBarInTeamStanding.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    standingAdapter.differ.submitList(it.data?.toList())
                    progressBarInTeamStanding.visibility = View.GONE
                }

                Status.ERROR -> {
                    progressBarInTeamStanding.visibility = View.VISIBLE
                    Toast.makeText(activity, "An error occurred", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        standingAdapter = StandingAdapter()
        rvStandingragment.apply {
            adapter = standingAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}