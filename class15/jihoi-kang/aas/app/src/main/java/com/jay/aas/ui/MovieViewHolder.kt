package com.jay.aas.ui

import android.os.Build
import android.text.Html
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jay.aas.databinding.ItemMovieBinding
import com.jay.aas.model.Movie

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val onItemClick: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var movie: Movie? = null

    init {
        binding.root.setOnClickListener {
            movie?.link?.let(onItemClick)
        }
    }

    @Suppress("DEPRECATION")
    fun onBind(movie: Movie) {
        this.movie = movie
        binding.tvPubDate.text = movie.pubDate
        binding.tvTitle.text =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(movie.title, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(movie.title)
            }
        binding.tvUserRating.text = movie.userRating
        binding.llUserRating.isGone = movie.userRating == "0.00"
        binding.tvActor.text = movie.actor
        Glide.with(binding.root.context)
            .load(movie.image)
            .into(binding.ivPost)
    }

}