package io.github.jesterz91.study.presentation.ui

import android.view.LayoutInflater
import androidx.core.text.HtmlCompat
import io.github.jesterz91.study.databinding.ItemMovieBinding
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.presentation.common.BaseViewHolder

class MovieSearchViewHolder(layoutInflater: LayoutInflater) :
    BaseViewHolder<Movie, ItemMovieBinding>(ItemMovieBinding.inflate(layoutInflater)) {

    override fun bind(item: Movie) = with(binding) {

        glide.load(item.image).into(movieImage)

        movieTitle.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)

        movieSubtitle.text = item.subtitle

        movieDirector.text = item.director

        movieActor.text = item.actor

        moviePubDate.text = item.pubDate

        movieRating.rating = item.userRating
    }
}