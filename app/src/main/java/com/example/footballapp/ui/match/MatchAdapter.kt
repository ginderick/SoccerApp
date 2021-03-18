package com.example.footballapp.ui.match

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballapp.R
import com.example.footballapp.data.match.remote.response.Match
import com.example.footballapp.databinding.ItemMatchBinding

class MatchAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem.idEvent == newItem.idEvent
        }

        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MatchViewHolder(
            ItemMatchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val match = differ.currentList[position]
        (holder as MatchViewHolder).bind(match)
    }
}

class MatchViewHolder(
    private val binding: ItemMatchBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Match) {
        binding.apply {
            match = item
            tvHomeTeamName.text = item.strHomeTeam
            tvAwayTeamName.text = item.strAwayTeam
            tvHomeTeamScore.text = item.intHomeScore
            tvAwayTeamScore.text = item.intAwayScore
            tvLeagueName.text = item.strLeague
            tvMatchDate.text = item.dateEvent
            Glide.with(itemView.context)
                .load(item.strThumb)
                .into(ivMatchThumbnail)
            executePendingBindings()
            expandMatchButton.setImageResource(R.drawable.ic_baseline_expand_more_24)
            expandMatchButton.setOnClickListener {
                if (expandableLayout.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                    expandableLayout.visibility = View.VISIBLE
                    expandMatchButton.setImageResource(R.drawable.ic_baseline_expand_less_24)
                } else {
                    TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                    expandableLayout.visibility = View.GONE
                    expandMatchButton.setImageResource(R.drawable.ic_baseline_expand_more_24)
                }
            }
        }
    }
}