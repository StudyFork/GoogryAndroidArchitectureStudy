package io.github.jesterz91.study.presentation.ui

import android.view.LayoutInflater
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.presentation.common.BaseAdapter

class MovieSearchAdapter : BaseAdapter<Movie, MovieSearchViewHolder>() {

    override fun createViewHolder(layoutInflater: LayoutInflater): MovieSearchViewHolder {
        return MovieSearchViewHolder(layoutInflater)
    }
}