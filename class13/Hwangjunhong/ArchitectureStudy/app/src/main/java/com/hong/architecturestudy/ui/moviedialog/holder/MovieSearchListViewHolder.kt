package com.hong.architecturestudy.ui.moviedialog.holder

import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import com.hong.architecturestudy.databinding.ItemMovieSearchListBinding

class MovieSearchListViewHolder(private val binding: ItemMovieSearchListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieInfo: MovieInfo) {
        binding.movieInfo = movieInfo
        binding.executePendingBindings()
    }
}