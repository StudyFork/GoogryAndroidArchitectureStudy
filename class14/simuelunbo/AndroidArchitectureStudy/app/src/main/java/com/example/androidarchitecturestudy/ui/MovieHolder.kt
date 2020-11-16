package com.example.androidarchitecturestudy.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecturestudy.data.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieHolder(
    itemView: View,
    onItemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

    private var movie: Movie? = null

    init {
        itemView.setOnClickListener {
            movie?.link?.let(onItemClick)
        }
    }
    fun bind(movie: Movie) {
        this.movie = movie
        itemView.tv_movie_title.text = removeUselessTitle(movie.title)
        itemView.tv_pub_date.text = movie.pubDate
        itemView.tv_director_name.text = removeUselessDirector(movie.director)
        itemView.tv_movie_rate.text = movie.userRating
        Glide.with(itemView).load(movie.image).into(itemView.iv_movie)
    }

    private fun removeUselessTitle(title: String?): String? {
        return title?.replace("<b>", "")?.replace("</b>", "")
    }

    private fun removeUselessDirector(director: String?): String? {
        return director?.replace("|", "")
    }
}