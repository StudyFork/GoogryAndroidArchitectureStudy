package io.github.jesterz91.study.ui

import android.view.LayoutInflater
import io.github.jesterz91.study.common.BaseViewHolder
import io.github.jesterz91.study.databinding.ItemMovieBinding
import io.github.jesterz91.study.model.MoviePresentation

class MovieSearchViewHolder(layoutInflater: LayoutInflater) :
    BaseViewHolder<MoviePresentation, ItemMovieBinding>(ItemMovieBinding.inflate(layoutInflater)) {

    override fun bind(item: MoviePresentation) = with(binding) {
        movie = item
        executePendingBindings()
    }
}