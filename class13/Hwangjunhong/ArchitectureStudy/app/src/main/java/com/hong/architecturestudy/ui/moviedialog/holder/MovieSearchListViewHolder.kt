package com.hong.architecturestudy.ui.moviedialog.holder

import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.databinding.ItemMovieSearchListBinding
import com.hong.architecturestudy.ui.main.MainViewModel
import com.hong.data.source.local.entity.MovieInfo

class MovieSearchListViewHolder(private val binding: ItemMovieSearchListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieInfo: MovieInfo, vm: MainViewModel? = null) {
        binding.apply {
            this.movieInfo = movieInfo
            this.executePendingBindings()
        }
    }
}