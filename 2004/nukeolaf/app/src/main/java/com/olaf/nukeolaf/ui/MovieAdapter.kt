package com.olaf.nukeolaf.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olaf.nukeolaf.MovieItem
import com.olaf.nukeolaf.R

class MovieAdapter : RecyclerView.Adapter<MovieItemViewHolder>() {

    private val movies = mutableListOf<MovieItem>()

    fun setMovies(list: List<MovieItem>) {
        this.movies.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val inflatedView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movies_rv, parent, false)
        return MovieItemViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}