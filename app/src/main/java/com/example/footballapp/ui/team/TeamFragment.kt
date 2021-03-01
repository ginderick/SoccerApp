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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.footballapp.R
import com.example.footballapp.data.team.TeamRepositoryImpl
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


        teamViewModel.searchTeam("!")
        teamViewModel.searchTeam.observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Status.LOADING -> {
                }

                Status.SUCCESS -> {
                    Log.d("TeamFragment", response.data.toString())
                }
                Status.ERROR -> Toast.makeText(activity, "An error occured", Toast.LENGTH_LONG)
                    .show()
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