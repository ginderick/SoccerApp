package com.example.footballapp.ui.league

import android.os.Bundle
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
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.footballapp.R
import com.example.footballapp.others.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_league.*
import kotlinx.android.synthetic.main.fragment_team.*


@AndroidEntryPoint
class LeagueFragment : Fragment() {

    private val leagueViewModel: LeagueViewModel by viewModels()
    val args: LeagueFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val league = args.league

        val navController = findNavController()
        NavigationUI.setupWithNavController(toolbar, navController)

//        setupNavigation()
        setupSupportActionBar()




        leagueViewModel.getLeagueDetail(league.idLeague)
        leagueViewModel.leagueLiveData.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.LOADING -> progressBarInLeague.visibility = View.VISIBLE

                Status.SUCCESS -> {
                    textViewLeagueName.text = it.data?.strLeague
                    textViewLeagueCountry.text = it.data?.strLeague

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

//    private fun setupNavigation() {
//        val navController = findNavController()
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_league,
//                R.id.navigation_dashboard, // set all your top level destinations in here
//                R.id.navigation_notifications
//            )
//        )
//
//        toolbar.setupWithNavController(navController, appBarConfiguration)
//    }

    private fun setupSupportActionBar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }

}