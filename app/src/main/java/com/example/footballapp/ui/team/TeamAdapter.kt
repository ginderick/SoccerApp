package com.example.footballapp.ui.team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballapp.data.team.remote.response.Team
import com.example.footballapp.databinding.ItemTeamsBinding
import com.example.footballapp.ui.league.LeagueFragmentDirections

class TeamAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.idTeam == newItem.idTeam
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TeamViewHolder(
            ItemTeamsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val team = differ.currentList[position]
        (holder as TeamViewHolder).bind(team)



    }
}

class TeamViewHolder(
    private val binding: ItemTeamsBinding
) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.setClickListener {
            binding.team?.let { team ->
                navigateToTeam(team, it)
            }
        }
    }

    private fun navigateToTeam(team: Team, view: View) {
        val direction =
            LeagueFragmentDirections.actionNavigationLeagueToNavigationTeamDetail(
                team
            )
        view.findNavController().navigate(direction)

    }

    fun bind(item: Team) {
        binding.apply {
            team = item
            tvTeamName.text = item.strTeam

            Glide.with(itemView.context)
                .load(item.strTeamBadge)
                .into(imgTeamLogo)
            executePendingBindings()
        }
    }
}
