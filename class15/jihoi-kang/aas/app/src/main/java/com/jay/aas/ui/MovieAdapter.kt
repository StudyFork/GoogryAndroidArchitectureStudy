package com.jay.aas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jay.aas.R
import com.jay.aas.databinding.ItemMovieBinding
import com.jay.aas.model.Movie

class MovieAdapter(
    private val onItemClick: (String) -> Unit,
) : RecyclerView.Adapter<MovieViewHolder>() {

    private val movies: MutableList<Movie> = mutableListOf()

    fun setMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            DataBindingUtil.inflate<ItemMovieBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        return MovieViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

}