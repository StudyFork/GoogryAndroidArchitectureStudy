package com.example.googryandroidarchitecturestudy.ui.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.data.model.MovieResponse.Movie

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val image: ImageView = view.findViewById(R.id.movie_image)
    private val title: TextView = view.findViewById(R.id.movie_title)
    private val rating: TextView = view.findViewById(R.id.rating)
    private val director: TextView = view.findViewById(R.id.director)
    private val pubDate: TextView = view.findViewById(R.id.pub_date)
    private val actor: TextView = view.findViewById(R.id.actor)

    fun bind(movie: Movie) {
        title.text = movie.title
        pubDate.text = movie.pubDate
        actor.text = movie.actor
        director.text = movie.director
        rating.text = movie.userRating
        Glide.with(itemView.context).load(movie.image).into(image)
    }

}