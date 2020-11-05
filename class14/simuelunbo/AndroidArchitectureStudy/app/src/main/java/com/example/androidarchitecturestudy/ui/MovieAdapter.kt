package com.example.androidarchitecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecturestudy.R
import com.example.androidarchitecturestudy.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieHolder>() {
    private val movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
       holder.bind(movies[position])
    }

    override fun getItemCount() = movies.count()

    fun setMovieList(movieList: List<Movie>){
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }
}