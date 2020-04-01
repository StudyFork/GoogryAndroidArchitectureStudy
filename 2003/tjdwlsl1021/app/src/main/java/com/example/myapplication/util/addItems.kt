package com.example.myapplication.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.local.MovieEntity
import com.example.myapplication.ui.SearchMovieAdapter

@BindingAdapter("addItems")
fun RecyclerView.addItems(items: List<MovieEntity>?) {
    with((adapter as SearchMovieAdapter)) {
        items?.let { this.addItems(it) }
    }
}