package io.github.jesterz91.study.presentation.ui

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.presentation.common.BaseAdapter

class MovieSearchAdapter : BaseAdapter<Movie, MovieSearchViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.link == newItem.link
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun createViewHolder(layoutInflater: LayoutInflater): MovieSearchViewHolder {
        return MovieSearchViewHolder(layoutInflater)
    }
}