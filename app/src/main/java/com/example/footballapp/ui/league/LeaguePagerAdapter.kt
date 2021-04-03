package com.example.footballapp.ui.league

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.footballapp.ui.match.MatchFragment
import com.example.footballapp.ui.standing.StandingFragment
import com.example.footballapp.ui.team.TeamFragment

class LeaguePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment, ){

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return MatchFragment()


            1 -> return TeamFragment()


            2 -> return StandingFragment()

            else -> return Fragment()
        }
    }
}