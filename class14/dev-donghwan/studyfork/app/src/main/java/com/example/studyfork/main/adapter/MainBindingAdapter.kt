package com.example.studyfork.main.adapter

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studyfork.MovieRecyclerAdapter
import com.example.studyfork.data.model.MovieSearchResponse

@BindingAdapter("main_bind:movieList")
fun RecyclerView.movieList(list: List<MovieSearchResponse.MovieItem>?) {
    list?.let { list ->
        (adapter as? MovieRecyclerAdapter)?.itemChange(list)
    }
}