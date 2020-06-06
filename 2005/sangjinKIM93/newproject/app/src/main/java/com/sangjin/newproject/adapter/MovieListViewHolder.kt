package com.sangjin.newproject.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sangjin.newproject.R
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.databinding.ItemMovieBinding
import kotlinx.android.extensions.LayoutContainer

class MovieListViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Movie) {
        binding.movie = item
        binding.executePendingBindings()
    }
}