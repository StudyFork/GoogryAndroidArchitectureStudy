package com.jay.architecturestudy.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jay.architecturestudy.databinding.ListItemMovieBinding
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder
import com.jay.architecturestudy.ui.OnItemClickListener
import com.jay.architecturestudy.util.startWebView
import com.jay.repository.model.Movie

internal class MovieAdapter : BaseAdapter<Movie, MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = ListItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieHolder(binding)
    }
}

internal class MovieHolder(
    val binding: ListItemMovieBinding
) : BaseViewHolder<Movie>(binding), OnItemClickListener {

    init {
        binding.clickEvent = this
    }

    override fun bind(item: Movie) {
        with(binding) {
            movie = item
            executePendingBindings()
        }
    }

    override fun onClick(v: View, url: String) {
        v.startWebView(url)
    }
}