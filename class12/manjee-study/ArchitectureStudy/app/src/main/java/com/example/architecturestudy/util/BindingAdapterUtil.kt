package com.example.architecturestudy.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.ui.MovieAdapter

@BindingAdapter("loadImageWithUrl")
fun loadImageWithUrl(imageView: ImageView, imgUrl: String) {
    Glide.with(imageView).load(imgUrl).into(imageView)
}

@BindingAdapter("showMovieList")
fun showMovieList(recyclerView: RecyclerView, movieList: List<MovieData>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    movieList?.let {
        adapter.setData(it)
    }
}
