package com.example.footballapp.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.footballapp.R
import com.example.footballapp.data.team.TeamRepositoryImpl
import com.example.footballapp.others.Resource
import com.example.footballapp.others.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TeamFragment : Fragment() {

    private val teamViewModel: TeamViewModel by viewModels()
//    private lateinit var teamViewModelFactory: TeamViewModelFactory

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
        teamViewModel.loadSearchTeam("Arsenal")
        teamViewModel.searchTeam.observe(viewLifecycleOwner, Observer {response ->
            when(response.status) {
                Status.LOADING -> {}

                Status.SUCCESS -> {}

                Status.ERROR -> Toast.makeText(activity, "An error occured", Toast.LENGTH_LONG).show()

            }


        })

    }
}