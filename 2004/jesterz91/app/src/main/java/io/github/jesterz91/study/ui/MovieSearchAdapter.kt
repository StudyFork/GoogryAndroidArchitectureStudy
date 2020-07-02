package io.github.jesterz91.study.ui

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import io.github.jesterz91.study.common.BaseAdapter
import io.github.jesterz91.study.model.MoviePresentation

class MovieSearchAdapter : BaseAdapter<MoviePresentation, MovieSearchViewHolder>(DIFF_CALLBACK) {

    override fun createViewHolder(layoutInflater: LayoutInflater): MovieSearchViewHolder {
        return MovieSearchViewHolder(layoutInflater)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviePresentation>() {
            override fun areItemsTheSame(oldItem: MoviePresentation, newItem: MoviePresentation): Boolean {
                return oldItem.link == newItem.link
            }

            override fun areContentsTheSame(oldItem: MoviePresentation, newItem: MoviePresentation): Boolean {
                return oldItem == newItem
            }
        }
    }
}