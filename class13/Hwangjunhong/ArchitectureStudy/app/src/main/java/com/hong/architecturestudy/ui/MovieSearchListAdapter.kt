package com.hong.architecturestudy.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.data.source.local.entity.MovieInfo

class MovieSearchListAdapter(var onClick: ((MovieInfo) -> Unit)) :
    RecyclerView.Adapter<MovieSearchListViewHolder>() {
    private val movieListItems = mutableListOf<MovieInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieSearchListViewHolder(parent).apply {
            itemView.setOnClickListener {
                onClick.invoke(movieListItems[absoluteAdapterPosition])
            }
        }

    override fun getItemCount() = movieListItems.size

    override fun onBindViewHolder(holder: MovieSearchListViewHolder, position: Int) {
        holder.bind(movieListItems[position])
    }

    fun setList(movieInfo: List<MovieInfo>) {
        movieListItems.clear()
        movieListItems.addAll(movieInfo)
        notifyDataSetChanged()
    }

}