package com.example.footballapp.ui.standing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballapp.R
import com.example.footballapp.data.standing.remote.response.Standing
import com.example.footballapp.databinding.ItemTeamStandingBinding

class StandingAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Standing>() {
        override fun areItemsTheSame(oldItem: Standing, newItem: Standing): Boolean {
            return oldItem.idTeam == newItem.idTeam
        }

        override fun areContentsTheSame(oldItem: Standing, newItem: Standing): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return StandingViewHolder(
                ItemTeamStandingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val standing = differ.currentList[position]
        (holder as StandingViewHolder).bind(standing)

    }

    override fun getItemCount(): Int = differ.currentList.size


    class HeaderViewHolder(headerView: View): RecyclerView.ViewHolder(headerView) {
        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.standing_header, parent, false)
                return HeaderViewHolder(view)
            }
        }
    }



    class StandingViewHolder(
        private val binding: ItemTeamStandingBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Standing) {
            binding.apply {
                standing = item

                tvTeamRank.text = item.intRank
                tvTeamName.text = item.strTeam
                tvTeamMatchesPlayed.text = item.intPlayed
                tvTeamWin.text = item.intWin
                tvTeamDraw.text = item.intDraw
                tvTeamLoss.text = item.intLoss
                tvTeamGoalsFor.text = item.intGoalsFor
                tvTeamGoalsDifference.text = item.intGoalDifference
                tvTeamGoalsAgainst.text = item.intGoalsAgainst
                tvTeamPoints.text = item.intPoints


                Glide.with(itemView.context)
                    .load(item.strTeamBadge)
                    .into(imgTeamLogo)
                executePendingBindings()
            }
        }
    }
}
