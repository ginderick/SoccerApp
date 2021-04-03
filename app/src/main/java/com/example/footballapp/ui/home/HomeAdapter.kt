package com.example.footballapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballapp.R
import com.example.footballapp.data.league.remote.response.League
import kotlinx.android.synthetic.main.item_leagues.view.*

class HomeAdapter() :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(data: League) {
            with(itemView) {
                tvLeagueName.text = data.strLeague

                Glide.with(itemView.context)
                    .load(data.strBadge)
                    .into(imgLeagues)

                setOnClickListener {
                    onItemClickListener?.let { it(data.idLeague) }
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<League>() {
        override fun areItemsTheSame(oldItem: League, newItem: League): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: League, newItem: League): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_leagues, viewGroup, false)

        return ViewHolder(view)
    }



    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val league = differ.currentList[position]
        viewHolder.bind(league)

    }

    override fun getItemCount() = differ.currentList.size

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    private var onItemClickListener: ((String) -> Unit)? = null

}
