package com.example.footballapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapp.R
import com.example.footballapp.databinding.FragmentSearchBinding
import com.example.footballapp.others.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        context ?: return binding.root

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        setupRecyclerView()

        searchViewModel.getCountry()
        searchViewModel.countryLiveData.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.LOADING -> {
                    progressBarInSearchFragment.visibility = View.VISIBLE

                }
                Status.SUCCESS -> {
                    Log.d("SearchFragment", it.data.toString())
                    searchAdapter.differ.submitList(it.data)
                    progressBarInSearchFragment.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBarInSearchFragment.visibility = View.GONE
                    Toast.makeText(activity, "An error occurred", Toast.LENGTH_LONG)
                        .show()

                }
            }
        })
    }



    private fun setupNavigation() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard, // set all your top level destinations in here
                R.id.navigation_search
            ) // don't forget the parentheses
        )

        toolbar.setupWithNavController(navController,appBarConfiguration)
    }

    private fun setupRecyclerView() {
        searchAdapter = SearchAdapter()
        rvCountryFragment.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}