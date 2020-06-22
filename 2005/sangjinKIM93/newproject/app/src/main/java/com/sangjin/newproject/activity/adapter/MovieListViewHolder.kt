package com.sangjin.newproject.activity.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.databinding.ItemMovieBinding

class MovieListViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Movie) {
        binding.movie = item
        binding.executePendingBindings()
    }
}