package com.example.googryandroidarchitecturestudy.ui.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.googryandroidarchitecturestudy.databinding.ItemMovieBinding
import com.example.googryandroidarchitecturestudy.domain.Movie

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    val onItemClick: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.view = this
    }

    fun bind(movie: Movie) {
        binding.movie = movie
    }

}