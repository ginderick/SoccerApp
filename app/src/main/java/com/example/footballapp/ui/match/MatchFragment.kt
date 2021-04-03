package com.example.footballapp.ui.match

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapp.databinding.FragmentMatchBinding
import com.example.footballapp.others.Status
import com.example.footballapp.ui.general.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_match.*


@AndroidEntryPoint
class MatchFragment : Fragment() {

    private lateinit var matchAdapter: MatchAdapter
    private val matchViewModel: MatchViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var leagueId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMatchBinding.inflate(inflater, container, false)
        context ?: return binding.root

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()


        //set leagueId
        sharedViewModel.leagueId.observe(viewLifecycleOwner, {
            leagueId = it
            matchViewModel.getMatchEvents(leagueId)
        })

        matchViewModel.matchEventList.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    progressBarInMatchEvents.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    matchAdapter.differ.submitList(it.data)
                    progressBarInMatchEvents.visibility = View.GONE
                }

                Status.ERROR -> {
                    progressBarInMatchEvents.visibility = View.GONE
                    Toast.makeText(activity, "An error occurred", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        matchAdapter = MatchAdapter()
        rvMatchFragment.apply {
            adapter = matchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}