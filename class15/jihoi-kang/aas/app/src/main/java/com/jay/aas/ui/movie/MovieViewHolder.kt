package com.jay.aas.ui.movie

import androidx.recyclerview.widget.RecyclerView
import com.jay.aas.databinding.ItemMovieBinding
import com.jay.aas.model.Movie

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val onItemClick: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var movie: Movie? = null

    init {
        binding.root.setOnClickListener {
            movie?.link?.let(onItemClick)
        }
    }

    fun onBind(movie: Movie) {
        this.movie = movie
        binding.item = movie
        binding.executePendingBindings()
    }

}