package com.example.aas.ui.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aas.R
import com.example.aas.data.model.Movie
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private val movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        ).apply {
            RxView.clicks(itemView)
                .throttleFirst(1000L, TimeUnit.MILLISECONDS)
                .subscribe {
                    val item = movieList[adapterPosition]
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                    itemView.context.startActivity(intent)
                }
        }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movieList[position])

    override fun getItemCount() = movieList.size

    fun setList(newMovieList: List<Movie>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }
}