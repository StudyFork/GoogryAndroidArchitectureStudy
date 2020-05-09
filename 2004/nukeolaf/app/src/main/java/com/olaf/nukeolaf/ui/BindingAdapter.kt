package com.olaf.nukeolaf.ui

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.olaf.nukeolaf.R

@BindingAdapter("movieImage")
fun loadMovieImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .error(R.drawable.ic_launcher_foreground)
        .into(view)
}

@BindingAdapter("setOnQueryTextListener")
fun setOnQueryTextListener(view: SearchView, searchMovie: (String?) -> Unit) {
    view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            searchMovie(query)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })
}