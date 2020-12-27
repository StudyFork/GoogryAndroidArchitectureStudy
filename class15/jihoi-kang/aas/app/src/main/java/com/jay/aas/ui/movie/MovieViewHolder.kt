package com.jay.aas.ui.movie

import androidx.recyclerview.widget.RecyclerView
import com.jay.aas.databinding.ItemMovieBinding
import com.jay.data.model.Movie

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val onItemClick: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            binding.item?.link?.let(onItemClick)
        }
    }

    fun onBind(movie: Movie) {
        binding.item = movie
        binding.executePendingBindings()
    }

}