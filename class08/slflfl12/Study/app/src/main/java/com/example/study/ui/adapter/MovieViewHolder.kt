package com.example.study.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.study.data.model.Movie
import com.example.study.databinding.MovieItemBinding

class MovieViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movie = movie
    }

}