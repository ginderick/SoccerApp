package com.example.footballapp.ui.league

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.example.footballapp.R
import com.example.footballapp.data.league.remote.response.League
import com.example.footballapp.others.Status
import com.example.footballapp.ui.team.TeamAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_league.*


@AndroidEntryPoint
class LeagueFragment : Fragment() {



    private val leagueViewModel: LeagueViewModel by viewModels()
    private lateinit var leaguePagerAdapter: LeaguePagerAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var league: League
    private lateinit var teamAdapter: TeamAdapter
    private val args: LeagueFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        league = args.league

        teamAdapter = TeamAdapter()
        setupNavigation()
        setupSupportActionBar()
        setupViewPager()
        setupSharedLeagueIdPref(league.idLeague)


        leagueViewModel.getLeagueDetail(league.idLeague)
        leagueViewModel.leagueLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> progressBarInLeague.visibility = View.VISIBLE

                Status.SUCCESS -> {
                    textViewLeagueName.text = it.data?.strLeague
                    textViewLeagueCountry.text = it.data?.strCountry

                    Glide
                        .with(requireContext())
                        .load(it.data?.strBadge)
                        .placeholder(R.drawable.ic_image_placeholder)
                        .into(imgLeague)
                    progressBarInLeague.visibility = View.GONE
                }

                Status.ERROR -> {
                    progressBarInLeague.visibility = View.GONE
                    Toast.makeText(activity, "An error occurred", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }



    private fun setupSupportActionBar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }

    private fun setupNavigation() {
        val navController = findNavController()
        NavigationUI.setupWithNavController(toolbar, navController)
    }

    private fun setupViewPager() {
        leaguePagerAdapter = LeaguePagerAdapter(this)
        viewPager.adapter = leaguePagerAdapter

        val pageTitles = listOf(
            getString(R.string.title_tab_match),
            getString(R.string.title_tab_team),
            getString(R.string.title_tab_standing)
        )

        TabLayoutMediator(tab_layout, viewPager) { tab, position ->
            tab.text = pageTitles[position]
        }.attach()
    }

    private fun setupSharedLeagueIdPref(leagueId: String) {
        sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPreferences.edit()) {
            putString("league", null)
            apply()
        }
    }

}