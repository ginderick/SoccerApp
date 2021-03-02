package com.example.footballapp.ui.team

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.footballapp.R
import com.example.footballapp.data.team.TeamRepositoryImpl
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.others.Resource
import com.example.footballapp.others.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_team.*
import javax.inject.Inject

@AndroidEntryPoint
class TeamFragment : Fragment() {

    private val teamViewModel: TeamViewModel by viewModels()

    @Inject
    lateinit var teamRepositoryImpl: TeamRepositoryImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        NavigationUI.setupWithNavController(mytoolbar, navController)

        setupSupportActionBar()
        setupCollapsingToolbarLayout()


        teamViewModel.searchTeam("Bayern")
        teamViewModel.searchTeam.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    progressBarInTeamDetail.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    it.data?.apply {
                        tvValueYear.text = this[0].intFormedYear
                        tvValueCountry.text = this[0].strCountry
                        tvLeague.text = this[0].strLeague
                        tvValueDescription.text = this[0].strDescriptionEN
                        tvValueStadium.text = this[0].strStadium

                        Glide
                            .with(requireContext())
                            .load(this[0].strTeamBadge)
                            .placeholder(R.drawable.ic_image_placeholder)
                            .into(imageTeamLogo)
                    }
                    progressBarInTeamDetail.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBarInTeamDetail.visibility = View.GONE
                    Toast.makeText(activity, "An error occurred", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })

    }

    private fun setupCollapsingToolbarLayout() {
        toolbarLayout.apply {
            setCollapsedTitleTextColor(
                ContextCompat.getColor(requireContext(), R.color.colorWhite)
            )
            setExpandedTitleColor(
                ContextCompat.getColor(requireContext(), R.color.colorTransparent)
            )
        }
    }

    private fun setupSupportActionBar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(mytoolbar)

        mytoolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }


}