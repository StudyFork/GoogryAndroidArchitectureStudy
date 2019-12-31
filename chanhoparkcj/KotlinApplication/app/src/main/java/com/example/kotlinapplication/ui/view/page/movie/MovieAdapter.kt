package com.example.kotlinapplication.ui.view.page.movie

import android.view.ViewGroup
import com.example.kotlinapplication.data.model.MovieItem
import com.example.kotlinapplication.ui.base.BaseAdapter

class MovieAdapter(private val listener: (MovieItem) -> Unit) :
    BaseAdapter<MovieItem, MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(parent, listener)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(items[position])
}