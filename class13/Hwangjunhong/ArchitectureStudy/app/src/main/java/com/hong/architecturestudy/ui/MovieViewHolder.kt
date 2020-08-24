package com.hong.architecturestudy.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.MovieData
import com.hong.architecturestudy.ext.htmlToText
import com.hong.architecturestudy.ext.loadGlideImageView
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
        moviePoster.loadGlideImageView(movieData.image)
        movieActor.text = htmlToText(movieData.actor)
        movieTitle.text = htmlToText(movieData.title)
        movieDirector.text = htmlToText(movieData.director)
        movieRating.rating = (movieData.userRating.toFloatOrNull() ?: 0f) / 2
        movieOpenDate.text = htmlToText(movieData.pubDate)

        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieData.link))
            itemView.context.startActivity(intent)
        }
    }
}