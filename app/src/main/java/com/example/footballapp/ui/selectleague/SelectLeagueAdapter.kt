package com.example.footballapp.ui.selectleague

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballapp.data.selectleague.remote.response.SelectLeague
import com.example.footballapp.databinding.ItemSelectLeagueBinding
import com.example.footballapp.ui.search.SearchFragmentDirections

class SelectLeagueAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<SelectLeague>() {
        override fun areItemsTheSame(oldItem: SelectLeague, newItem: SelectLeague): Boolean {
            return oldItem.strLeague == newItem.strLeague
        }

        override fun areContentsTheSame(oldItem: SelectLeague, newItem: SelectLeague): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SelectLeagueViewHolder(
            ItemSelectLeagueBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val league = differ.currentList[position]
        (holder as SelectLeagueViewHolder).bind(league)
    }
}

class SelectLeagueViewHolder(
    private val binding: ItemSelectLeagueBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setClickListener {
            binding.league?.let { league ->
                navigateToLeague(league.idLeague, it)
            }
        }
    }

    private fun navigateToLeague(idLeague: String, view: View) {
        val direction =
            SelectLeagueFragmentDirections.actionSelectLeagueFragmentToNavigationLeague(
                idLeague
            )
        view.findNavController().navigate(direction)
    }

    fun bind(item: SelectLeague) {
        binding.apply {
            league = item
            Glide.with(itemView.context)
                .load(item.strBadge)
                .into(imgSelectLeagues)

            tvSelectLeagueName.text = item.strLeague


        }
    }
}