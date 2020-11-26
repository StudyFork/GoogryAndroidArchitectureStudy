package com.example.androidarchitecturestudy.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.databinding.ItemMovieBinding

class MovieHolder(
    private val binding: ItemMovieBinding,
    onItemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

    private var movie: Movie? = null

    init {
        binding.root.setOnClickListener {
            movie?.link?.let(onItemClick)
        }
    }
    fun bind(movie: Movie) {
        this.movie = movie
        binding.movie = movie
        binding.executePendingBindings()
    }
}