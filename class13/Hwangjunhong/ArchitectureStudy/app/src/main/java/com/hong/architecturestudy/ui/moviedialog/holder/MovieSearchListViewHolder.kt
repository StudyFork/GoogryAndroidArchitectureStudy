package com.hong.architecturestudy.ui.moviedialog.holder

import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import com.hong.architecturestudy.databinding.ItemMovieSearchListBinding
import com.hong.architecturestudy.ui.main.MainViewModel

class MovieSearchListViewHolder(private val binding: ItemMovieSearchListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieInfo: MovieInfo, vm: MainViewModel? = null) {

        vm.let {
            binding.apply {
                this.movieInfo = movieInfo
                this.mainVm = vm
                this.executePendingBindings()
            }
        }
    }
}