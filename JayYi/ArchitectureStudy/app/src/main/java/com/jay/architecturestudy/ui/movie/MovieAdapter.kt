package com.jay.architecturestudy.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Movie
import com.jay.architecturestudy.databinding.ListItemMovieBinding
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder
import com.jay.architecturestudy.ui.OnItemClickListener
import com.jay.architecturestudy.util.startWebView

internal class MovieAdapter : BaseAdapter<Movie, MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = DataBindingUtil.inflate<ListItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_movie,
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