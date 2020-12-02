package com.hhi.myapplication

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.main.MainRecyclerAdapter

@BindingAdapter("setMovieList")
fun RecyclerView.setMovieList(items: ArrayList<MovieData.MovieItem>?) {
    if (adapter == null) {
        setHasFixedSize(false)
        adapter = MainRecyclerAdapter()
    }

    items?.let {
        (adapter as MainRecyclerAdapter).setMovieList(it)
    }
}