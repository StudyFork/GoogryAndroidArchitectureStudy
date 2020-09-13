package com.hong.architecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.model.MovieData
import com.hong.architecturestudy.ext.loadImageView
import com.hong.architecturestudy.ext.toHtml
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
) {

    private val movieActor = itemView.tv_actor
    private val movieTitle = itemView.tv_title
    private val moviePoster = itemView.iv_poster
    private val movieDirector = itemView.tv_director
    private val movieRating = itemView.rating_bar
    private val movieOpenDate = itemView.tv_opening_date

    fun onBind(movieData: MovieData) {
        movieTitle.text = movieData.title.toHtml()
        movieActor.text = movieData.actor.toHtml()
        movieDirector.text = movieData.director.toHtml()
        movieOpenDate.text = movieData.pubDate.toHtml()
        moviePoster.loadImageView(movieData.image)
        movieRating.rating = (movieData.userRating.toFloatOrNull() ?: 0f) / 2

    }
}