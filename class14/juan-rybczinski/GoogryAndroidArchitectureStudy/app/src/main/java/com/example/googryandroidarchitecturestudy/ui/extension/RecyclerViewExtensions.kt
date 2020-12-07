package com.example.googryandroidarchitecturestudy.ui.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.ui.recycler.MovieAdapter

@BindingAdapter("list")
fun RecyclerView.setList(list: List<Movie>) {
    (adapter as? MovieAdapter)?.setMovies(list)
}