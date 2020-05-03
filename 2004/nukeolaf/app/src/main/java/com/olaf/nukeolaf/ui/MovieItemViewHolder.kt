package com.olaf.nukeolaf.ui

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.olaf.nukeolaf.data.model.MovieItem
import com.olaf.nukeolaf.databinding.ItemMoviesRvBinding

class MovieItemViewHolder(private val binding: ItemMoviesRvBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var movieItem: MovieItem

    init {
        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieItem.link))
            it.context.startActivity(intent)
        }
    }

    fun bind(item: MovieItem) {
        movieItem = item
        with(binding) {
            movieItem = item
            executePendingBindings()
        }
    }
}