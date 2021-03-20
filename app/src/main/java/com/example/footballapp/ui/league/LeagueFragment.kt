package com.example.footballapp.ui.league

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.example.footballapp.R
import com.example.footballapp.data.league.remote.response.League
import com.example.footballapp.others.Status
import com.example.footballapp.ui.general.SharedViewModel
import com.example.footballapp.ui.team.TeamAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_league.*


@AndroidEntryPoint
class LeagueFragment : Fragment() {



    private val leagueViewModel: LeagueViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()


    private lateinit var leaguePagerAdapter: LeaguePagerAdapter
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
        socialMediaValidator()


        leagueViewModel.getLeagueDetail(league.idLeague)
        sharedViewModel.setLeagueId(league.idLeague)
        leagueViewModel.leagueLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> progressBarInLeague.visibility = View.VISIBLE

                Status.SUCCESS -> {
                    textViewLeagueName.text = it.data?.strLeague
                    textViewLeagueCountry.text = it.data?.strCountry
                    img_fb_logo.setImageResource(R.drawable.logo_facebook)
                    img_twitter_logo.setImageResource(R.drawable.logo_twitter)
                    img_youtube_logo.setImageResource(R.drawable.logo_youtube)

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

    private fun socialMediaValidator() {

        if (league.strFacebook != null) {
            img_fb_logo.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("url", league.strFacebook)
                }
                findNavController().navigate(
                    R.id.action_navigation_league_to_socialMediaFragment,
                    bundle
                )
            }
        } else {
            Toast.makeText(activity, "Facebook account not available", Toast.LENGTH_SHORT).show()
        }

        if (league.strTwitter != null) {
            img_twitter_logo.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("url", league.strTwitter)
                }
                findNavController().navigate(
                    R.id.action_navigation_league_to_socialMediaFragment,
                    bundle
                )
            }
        } else {
            Toast.makeText(activity, "Twitter account not available", Toast.LENGTH_SHORT).show()
        }

        if (league.strYoutube != null) {
            img_youtube_logo.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("url", league.strYoutube)
                }
                findNavController().navigate(
                    R.id.action_navigation_league_to_socialMediaFragment,
                    bundle
                )
            }
        } else {
            Toast.makeText(activity, "Twitter account not available", Toast.LENGTH_SHORT).show()
        }
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
}