package com.example.aas.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aas.R
import com.example.aas.data.model.Movie

class MovieAdapter(private val callback: MovieSelectionListener) :
    RecyclerView.Adapter<MovieViewHolder>() {
    val movieList = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        ).apply { binding.callback = callback }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movieList[position])

    override fun getItemCount() = movieList.size

    fun setList(newMovieList: List<Movie>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

    interface MovieSelectionListener {
        fun onMovieSelect(uri: String)
    }
}