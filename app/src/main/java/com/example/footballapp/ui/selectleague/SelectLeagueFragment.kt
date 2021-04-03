package com.example.footballapp.ui.selectleague

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapp.R
import com.example.footballapp.data.league.remote.response.League
import com.example.footballapp.ui.league.LeagueFragmentArgs
import com.example.footballapp.ui.match.MatchAdapter
import com.example.footballapp.ui.match.MatchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_select_league.*

@AndroidEntryPoint
class SelectLeagueFragment : Fragment() {

    private val selectLeagueViewModel: SelectLeagueViewModel by viewModels()
    private lateinit var selectLeagueAdapter: SelectLeagueAdapter
    private val args: SelectLeagueFragmentArgs by navArgs()

    private lateinit var country: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        country = args.country

        setupRecyclerView()
        selectLeagueViewModel.getAllLeagues(country, "soccer")
        selectLeagueViewModel.selectLeagueLiveData.observe(viewLifecycleOwner, {
            selectLeagueAdapter.differ.submitList(it.data)
        })

    }

    private fun setupRecyclerView() {
        selectLeagueAdapter = SelectLeagueAdapter()
        rvSelectLeague.apply {
            adapter = selectLeagueAdapter
            layoutManager = GridLayoutManager(activity, 3)
        }
    }
}