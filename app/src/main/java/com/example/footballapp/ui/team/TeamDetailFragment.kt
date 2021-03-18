package com.example.footballapp.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.example.footballapp.R
import com.example.footballapp.data.team.TeamRepositoryImpl
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.others.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_team_detail.*
import javax.inject.Inject

@AndroidEntryPoint
class TeamDetailFragment : Fragment() {


    @Inject
    lateinit var teamRepositoryImpl: TeamRepositoryImpl

    private val teamViewModel: TeamViewModel by viewModels()
    private lateinit var team: Team

    private val args: TeamDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBarInTeamDetail.visibility = View.GONE
        team = args.team

        setupNavigation()
        setupSupportActionBar()
        setupCollapsingToolbarLayout()


        teamViewModel.searchTeam(team.idTeam)
        teamViewModel.searchTeamLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    progressBarInTeamDetail.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    it.data?.apply {
                        tvValueYear.text = team.intFormedYear
                        tvValueCountry.text = this.strCountry
                        tvLeague.text = this.strLeague
                        tvValueDescription.text = this.strDescriptionEN
                        tvValueStadium.text = this.strStadium
                        toolbarLayout.title = this.strTeam

                        Glide
                            .with(requireContext())
                            .load(this.strStadiumThumb)
                            .placeholder(R.drawable.ic_image_placeholder)
                            .into(img_team_stadium)

                        Glide
                            .with(requireContext())
                            .load(this.strTeamBadge)
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

    private fun setupNavigation() {
        val navController = findNavController()
        NavigationUI.setupWithNavController(mytoolbar, navController)
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