package com.olaf.nukeolaf.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.olaf.nukeolaf.R
import com.olaf.nukeolaf.data.model.MovieItem
import com.olaf.nukeolaf.databinding.ItemMoviesRvBinding

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
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMoviesRvBinding>(
            inflater,
            R.layout.item_movies_rv,
            parent,
            false
        )
        return MovieItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}