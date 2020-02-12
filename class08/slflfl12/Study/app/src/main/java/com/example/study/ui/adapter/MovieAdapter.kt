package com.example.study.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.study.R
import com.example.study.data.model.Movie
import com.example.study.databinding.MovieItemBinding


class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = DataBindingUtil.inflate<MovieItemBinding>(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)
        val holder = MovieViewHolder(binding)

        return holder
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }


    fun setItem(item: List<Movie>) {
        movies.clear()
        movies.addAll(item)
        notifyDataSetChanged()
    }

    override fun getItemCount() = movies.size



}


