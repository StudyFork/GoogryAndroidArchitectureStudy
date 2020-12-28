package com.deepco.studyfork

import androidx.recyclerview.widget.RecyclerView
import com.deepco.data.data.model.Item
import com.deepco.studyfork.databinding.MovieItemBinding

class MovieViewHolder(
    private val binding: MovieItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(myModel: Item) {
        binding.movie = myModel
        binding.executePendingBindings()
    }
}