package com.example.hw2_project.recentSearch

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2_project.R
import com.example.hw2_project.databinding.RecentMovieItemBinding
import com.example.hw2_project.main.MainActivity

class RecentSearchRecyclerViewAdapter(
    private val clickListener: SavedMovieClickListener
) : RecyclerView.Adapter<RecentSearchRecyclerViewAdapter.ViewHolder>() {

    private val queryList = mutableListOf<String>()

    fun updateMovieList(list: List<String>?) {
        list?.run {
            queryList.clear()
            queryList.addAll(this)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DataBindingUtil.inflate<RecentMovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recent_movie_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return queryList.size
    }

    override fun onBindViewHolder(
        holder: RecentSearchRecyclerViewAdapter.ViewHolder, position: Int
    ) {
        val query = queryList[position]
        holder.bind(query)
    }

    inner class ViewHolder(private val recentMovieItemBinding: RecentMovieItemBinding) :
        RecyclerView.ViewHolder(recentMovieItemBinding.root) {
        fun bind(query: String) {
            recentMovieItemBinding.recentItemTextTitle.text = query
            recentMovieItemBinding.recentItemTextTitle.setOnClickListener {
                clickListener.clickSavedMovie(query)
            }
            recentMovieItemBinding.executePendingBindings()
        }
    }

    interface SavedMovieClickListener {
        fun clickSavedMovie(movie: String)
    }

}