package com.hong.architecturestudy.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import kotlinx.android.synthetic.main.item_movie_search_list.view.*

class MovieSearchListAdapter : RecyclerView.Adapter<MovieSearchListViewHolder>() {
    private val movieListItems = mutableListOf<MovieInfo>()
    lateinit var onClick: ((String) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieSearchListViewHolder(parent).apply {
            itemView.setOnClickListener {
                onClick.invoke(it.tv_movie_title.text.toString())
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