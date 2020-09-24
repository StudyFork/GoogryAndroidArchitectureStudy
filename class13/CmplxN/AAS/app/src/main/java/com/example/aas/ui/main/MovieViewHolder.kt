package com.example.aas.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.example.aas.data.model.Movie
import com.example.aas.databinding.ItemMovieBinding

class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(_movie: Movie) {
        binding.run {
            movie = _movie
            executePendingBindings()
        }
    }
}