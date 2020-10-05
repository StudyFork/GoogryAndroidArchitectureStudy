package com.hjhan.hyejeong.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hjhan.hyejeong.data.model.Item

@BindingAdapter("setListMovie")
fun RecyclerView.setListMovie(list: List<Item>?) {

    if (list == null) return

    val movieAdapter : MovieAdapter

    if (adapter == null) {
        movieAdapter = MovieAdapter()
        adapter = movieAdapter
    } else {
        movieAdapter = adapter as MovieAdapter
    }

    movieAdapter.setMovieList(list)

}

@BindingAdapter("setListQuery")
fun RecyclerView.setListQuery(list: List<String>?) {

    if (list == null) return

    val historyAdapter : QueryHistoryAdapter

    if (adapter == null) {
        historyAdapter = QueryHistoryAdapter()
        adapter = historyAdapter
    } else {
        historyAdapter = adapter as QueryHistoryAdapter
    }

    historyAdapter.setMovieList(list)

}