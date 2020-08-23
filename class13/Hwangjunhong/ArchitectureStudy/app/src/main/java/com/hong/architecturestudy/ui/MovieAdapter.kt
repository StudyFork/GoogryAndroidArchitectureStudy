package com.hong.architecturestudy.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.data.MovieData

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private val items = mutableListOf<MovieData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    fun addItem(item: List<MovieData>){
        items.clear()
        items.addAll(item)
        notifyDataSetChanged()
    }



}