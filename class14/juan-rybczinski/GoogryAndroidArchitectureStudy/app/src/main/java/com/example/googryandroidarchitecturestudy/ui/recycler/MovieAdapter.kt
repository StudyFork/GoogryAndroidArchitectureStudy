package com.example.googryandroidarchitecturestudy.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.googryandroidarchitecturestudy.databinding.ItemMovieBinding
import com.example.googryandroidarchitecturestudy.domain.Movie

class MovieAdapter(
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {
    private val movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    fun setMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }
}