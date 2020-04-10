package com.olaf.nukeolaf

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(
    private val itemListener: MovieItemListener
) : RecyclerView.Adapter<MovieItemVeiwHolder>() {

    private val movies = mutableListOf<MovieItem>()

    fun setMovies(list: List<MovieItem>) {
        this.movies.apply {
            clear()
            addAll(list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemVeiwHolder {
        val inflatedView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movies_rv, parent, false)
        return MovieItemVeiwHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieItemVeiwHolder, position: Int) {
        holder.bind(movies[position])
    }
}