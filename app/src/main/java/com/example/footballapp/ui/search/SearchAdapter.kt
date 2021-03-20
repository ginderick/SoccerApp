package com.example.footballapp.ui.search


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballapp.data.country.remote.response.Country
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.databinding.ItemCountryBinding
import com.example.footballapp.databinding.ItemTeamsBinding
import com.example.footballapp.ui.league.LeagueFragmentDirections

class SearchAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.countries == newItem.countries
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val country = differ.currentList[position]
        (holder as SearchViewHolder).bind(country)
    }
}

class SearchViewHolder(
    private val binding: ItemCountryBinding
) :
    RecyclerView.ViewHolder(binding.root) {
//    init {
//        binding.setClickListener {
//            binding.country?.let { country ->
//                navigateToTeam(country, it)
//            }
//        }
//    }

//    private fun navigateToTeam(country: Country, view: View) {
//        val direction =
//            LeagueFragmentDirections.actionNavigationLeagueToNavigationTeamDetail(
//                country
//            )
//        view.findNavController().navigate(direction)
//    }

    fun bind(item: Country) {
        binding.apply {
            country = item
            tvCountry.text = item.countries
            executePendingBindings()
        }
    }
}
