package com.hong.architecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import kotlinx.android.synthetic.main.item_movie_search_list.view.*

class MovieSearchListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_movie_search_list, parent, false)
) {

    private val movieTitle = itemView.tv_movie_title

    fun bind(movieInfo: MovieInfo) {
        movieTitle.text = movieInfo.movieTitle
    }

}