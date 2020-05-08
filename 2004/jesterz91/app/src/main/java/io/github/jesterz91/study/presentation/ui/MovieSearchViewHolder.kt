package io.github.jesterz91.study.presentation.ui

import android.view.LayoutInflater
import io.github.jesterz91.study.databinding.ItemMovieBinding
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.presentation.common.BaseViewHolder

class MovieSearchViewHolder(layoutInflater: LayoutInflater) :
    BaseViewHolder<Movie, ItemMovieBinding>(ItemMovieBinding.inflate(layoutInflater)) {

    override fun bind(item: Movie) = with(binding) {
        movie = item
        executePendingBindings()
    }
}