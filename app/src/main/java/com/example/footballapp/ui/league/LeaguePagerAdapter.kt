package com.example.footballapp.ui.league

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.footballapp.ui.dashboard.DashboardFragment
import com.example.footballapp.ui.notifications.MatchFragment
import com.example.footballapp.ui.team.TeamDetailFragment
import com.example.footballapp.ui.team.TeamFragment

class LeaguePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment, ){

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        when(position) {
            0 -> {
                return MatchFragment()
            }

            else -> {
                return TeamFragment()
            }
        }
    }
}