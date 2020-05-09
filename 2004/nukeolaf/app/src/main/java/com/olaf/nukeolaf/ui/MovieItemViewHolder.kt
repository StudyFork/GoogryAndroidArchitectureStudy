package com.olaf.nukeolaf.ui

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.olaf.nukeolaf.data.model.MovieItem
import com.olaf.nukeolaf.databinding.ItemMoviesRvBinding

class MovieItemViewHolder(private val binding: ItemMoviesRvBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.movieItem.link))
            it.context.startActivity(intent)
        }
    }

    fun bind(item: MovieItem) {
        with(binding) {
            movieItem = item
            executePendingBindings()
        }
    }
}