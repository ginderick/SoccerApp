package com.example.footballapp.ui.league

import android.os.Bundle
import android.util.Log
import android.view.*

import android.widget.Toast
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
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_league.*


@AndroidEntryPoint
class LeagueFragment : Fragment() {


    private val leagueViewModel: LeagueViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()


    private lateinit var leaguePagerAdapter: LeaguePagerAdapter
    private lateinit var league: League
    private lateinit var idleague: String
    private lateinit var teamAdapter: TeamAdapter
    private val args: LeagueFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        idleague = args.idLeague
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        teamAdapter = TeamAdapter()
        setupNavigation()
        setupViewPager()
        setupToolBar()


        leagueViewModel.getLeagueDetail(idleague)
        sharedViewModel.setLeagueId(idleague)
        leagueViewModel.leagueLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> progressBarInLeague.visibility = View.VISIBLE

                Status.SUCCESS -> {
                    textViewLeagueName.text = it.data?.strLeague
                    textViewLeagueCountry.text = it.data?.strCountry
                    img_fb_logo.setImageResource(R.drawable.logo_facebook)
                    img_twitter_logo.setImageResource(R.drawable.logo_twitter)
                    img_youtube_logo.setImageResource(R.drawable.logo_youtube)

                    socialMediaValidator(it.data!!)
                    league = it.data

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

    private fun socialMediaValidator(league: League) {

        if (!league.strFacebook.isNullOrEmpty()) {
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
            img_fb_logo.setOnClickListener {
                Toast.makeText(activity, "Facebook account not available", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        if (!league.strTwitter.isNullOrEmpty()) {
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
            img_twitter_logo.setOnClickListener {
                Toast.makeText(activity, "Twitter account not available", Toast.LENGTH_SHORT).show()
            }

        }

        if (!league.strYoutube.isNullOrEmpty()) {
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
            img_youtube_logo.setOnClickListener {
                Toast.makeText(activity, "Youtube account not available", Toast.LENGTH_SHORT).show()
            }
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

    private fun setupToolBar() {
        toolbar.inflateMenu(R.menu.league_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.leagueAdd -> {
                    leagueViewModel.saveLeague(league)
                    Snackbar.make(requireView(), "Successfully saved League", Snackbar.LENGTH_SHORT)
                        .show()
                    Log.d("LeagueFragment", "Add called")
                    true
                }
                else -> false
            }
        }

        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }
}