package com.onit.googlearchitecturestudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.holder_movie.view.*

class ResultMovieListRecyclerAdapter(
    private var list: List<Movie>,
    private val listener: MovieViewHolder.ClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_movie, parent, false)

        return MovieViewHolder(itemView, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = list[position]
        with(holder as MovieViewHolder) {
            title.text = movie.title
            rating.rating = movie.userRating
            releaseDate.text = movie.releaseDate
            director.text = movie.director
            actor.text = movie.actor
        }
    }

    class MovieViewHolder(itemView: View, private val listener: ClickListener) :
        RecyclerView.ViewHolder(itemView) {

        private val movieViewHolder = itemView.movieHolder
        var poster = itemView.posterImageView
        var title = itemView.titleTextView
        var rating = itemView.gradeRatingBar
        var releaseDate = itemView.releaseDateTextView
        var director = itemView.directorTextView
        var actor = itemView.actorsTextView

        init {
            movieViewHolder.setOnClickListener {
                listener.clickViewHolder(adapterPosition)
            }
        }

        interface ClickListener {
            fun clickViewHolder(position: Int)
        }
    }
}