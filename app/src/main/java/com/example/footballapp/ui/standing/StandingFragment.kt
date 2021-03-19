package com.example.footballapp.ui.standing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.footballapp.R
import com.example.footballapp.others.Status
import com.example.footballapp.ui.team.TeamViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_team.*

@AndroidEntryPoint
class StandingFragment : Fragment() {

    private val standingViewModel: StandingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.standing_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        standingViewModel.getLeagueTable("4328")
        standingViewModel.leagueTableLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                }

                Status.SUCCESS -> {
                    Log.d("StandingFragment", it.data.toString())
                }

                Status.ERROR -> {
                    Toast.makeText(activity, "An error occurred", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })



    }
}