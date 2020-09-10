package com.hong.architecturestudy.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.data.source.local.entity.MovieInfo

class MovieSearchListAdapter(val onClick: GetMovieTitle) :
    RecyclerView.Adapter<MovieSearchListViewHolder>() {
    private val movieItems = mutableListOf<MovieInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieSearchListViewHolder(parent).apply {
            itemView.setOnClickListener {
                onClick(movieItems[absoluteAdapterPosition].movieTitle)
            }
        }

    override fun getItemCount() = movieItems.size

    override fun onBindViewHolder(holder: MovieSearchListViewHolder, position: Int) {
        holder.bind(movieItems[position])
    }

    fun setList(movieInfo: List<MovieInfo>) {
        movieItems.clear()
        movieItems.addAll(movieInfo)
        notifyDataSetChanged()
    }

}