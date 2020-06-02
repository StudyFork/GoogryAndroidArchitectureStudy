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

        Glide.with(binding.movieImageIV.context)
            .load(item.image)
            .placeholder(R.drawable.img_default)
            .centerCrop()
            .into(binding.movieImageIV)
    }
}