package com.hong.architecturestudy.ui.main.holder

import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.databinding.ItemMovieListBinding
import com.hong.data.model.MovieData

class MovieViewHolder(private val binding: ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieData: MovieData) {
        binding.movieData = movieData
        binding.executePendingBindings()

    }
}
