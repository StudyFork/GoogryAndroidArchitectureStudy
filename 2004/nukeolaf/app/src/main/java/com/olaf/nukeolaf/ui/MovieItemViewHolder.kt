package com.olaf.nukeolaf.ui

import androidx.recyclerview.widget.RecyclerView
import com.olaf.nukeolaf.data.model.MovieItem
import com.olaf.nukeolaf.databinding.ItemMoviesRvBinding

class MovieItemViewHolder(private val binding: ItemMoviesRvBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieItem) {
        with(binding) {
            movieItem = item
            executePendingBindings()
        }
    }
}