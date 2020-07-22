package com.lllccww.studyforkproject.ui.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lllccww.data.model.MovieItem
import com.lllccww.studyforkproject.ui.main.MovieListAdapter


@BindingAdapter("setMovieItem")
fun RecyclerView.setItem(movieItem: List<MovieItem>?) {
    movieItem?.let { movieItem ->
        (adapter as MovieListAdapter).addItems(movieItem)
    }
}
