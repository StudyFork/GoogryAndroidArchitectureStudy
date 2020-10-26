package com.example.aas.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.example.aas.databinding.ItemMovieBinding
import com.example.datamodule.data.model.Movie

class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(_movie: Movie) {
        binding.run {
            movie = _movie
            executePendingBindings()
        }
    }
}