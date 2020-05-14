package com.example.studyforkandroid.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studyforkandroid.adapter.MovieAdapter
import com.example.studyforkandroid.data.Movie

@BindingAdapter(value = ["setItems"])
fun RecyclerView.setItems(movies: List<Movie>?) {
    movies?.let {
        (adapter as MovieAdapter).replaceAll(it)
    }
}