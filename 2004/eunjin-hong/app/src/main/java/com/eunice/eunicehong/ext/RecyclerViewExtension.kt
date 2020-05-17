package com.eunice.eunicehong.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eunice.eunicehong.data.model.Movie
import com.eunice.eunicehong.ui.MovieAdapter

@BindingAdapter("app:items")
fun setItems(recyclerView: RecyclerView, items: Collection<Movie>?) {
    (recyclerView.adapter as? MovieAdapter)?.setMovieList(items)
}