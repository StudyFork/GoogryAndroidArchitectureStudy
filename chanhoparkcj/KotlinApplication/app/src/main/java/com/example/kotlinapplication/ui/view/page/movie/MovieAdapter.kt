package com.example.kotlinapplication.ui.view.page.movie

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.data.model.MovieItem

class MovieAdapter(private val listener: (MovieItem)->Unit) :

    RecyclerView.Adapter<MovieViewHolder>() {
    private val items = mutableListOf<MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =MovieViewHolder(parent,listener)

    override fun getItemCount(): Int =items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(items[position])

    fun resetItems(ListItems: List<MovieItem>) {
        items.clear()
        items.addAll(ListItems)
        notifyDataSetChanged()
    }
}