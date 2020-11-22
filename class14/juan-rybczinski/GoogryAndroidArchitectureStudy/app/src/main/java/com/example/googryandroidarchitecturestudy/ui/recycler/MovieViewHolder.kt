package com.example.googryandroidarchitecturestudy.ui.recycler

import android.os.Build
import android.text.Html
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.databinding.ItemMovieBinding
import com.example.googryandroidarchitecturestudy.domain.Movie

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val onItemClick: (Movie) -> Unit,
    movies: List<Movie>
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            onItemClick(movies[adapterPosition])
        }
    }

    fun bind(movie: Movie) {
        binding.movieTitle.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(movie.title, Html.FROM_HTML_MODE_LEGACY)
        } else {
            HtmlCompat.fromHtml(movie.title, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
        binding.movie = movie
        Glide.with(itemView.context)
            .load(movie.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.movieImage)
    }

}