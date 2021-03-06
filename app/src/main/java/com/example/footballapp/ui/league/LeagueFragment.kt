package com.example.footballapp.ui.league

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.footballapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_league.*

@AndroidEntryPoint
class LeagueFragment : Fragment() {

    private lateinit var leagueViewModel: LeagueViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        leagueViewModel =
            ViewModelProvider(this).get(LeagueViewModel::class.java)
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()

        leagueViewModel.leagueLiveData.observe(viewLifecycleOwner, {
        })

        leagueViewModel.getLeagueList().observe(viewLifecycleOwner,  {
        })






        home_button.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_home_to_navigation_team
            )
        }
    }

    private fun setupNavigation() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_league,
                R.id.navigation_dashboard, // set all your top level destinations in here
                R.id.navigation_notifications
            )
        )

        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

}