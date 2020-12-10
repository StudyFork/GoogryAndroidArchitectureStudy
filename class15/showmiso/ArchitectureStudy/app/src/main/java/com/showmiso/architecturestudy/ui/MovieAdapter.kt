package com.showmiso.architecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.showmiso.architecturestudy.api.MovieModel
import com.showmiso.architecturestudy.databinding.ItemMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movies = mutableListOf<MovieModel.Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    fun setMovieList(movieList: List<MovieModel.Movie>) {
        movies.clear()
        movies.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel.Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

}