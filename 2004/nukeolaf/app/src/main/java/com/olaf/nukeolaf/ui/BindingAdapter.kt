package com.olaf.nukeolaf.ui

import android.widget.ImageView
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