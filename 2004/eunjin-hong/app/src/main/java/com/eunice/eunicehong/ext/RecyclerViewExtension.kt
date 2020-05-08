package com.eunice.eunicehong.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eunice.eunicehong.ui.MovieAdapter


@BindingAdapter("app:setMovieAdapter")
fun setMovieList(recyclerView: RecyclerView, movieAdapter: MovieAdapter) {
    recyclerView.adapter = movieAdapter
}
