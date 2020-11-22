package com.example.googryandroidarchitecturestudy.ui.recycler

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googryandroidarchitecturestudy.databinding.ItemMovieBinding
import com.example.googryandroidarchitecturestudy.domain.Movie

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val onItemClick: (Movie) -> Unit,
    movies: List<Movie>
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            onItemClick(movies[adapterPosition])
        }
    }

    fun bind(movie: Movie) {
        binding.movieTitle.text = movie.title
        binding.pubDate.text = movie.pubDate
        binding.actor.text = movie.actor
        binding.director.text = movie.director
        binding.rating.text = movie.userRating
        Glide.with(itemView.context).load(movie.image).into(binding.movieImage)
    }

}