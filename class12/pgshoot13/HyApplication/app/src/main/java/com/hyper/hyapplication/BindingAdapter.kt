package com.hyper.hyapplication

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun ImageUrl(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView)
        .load(imageUrl)
        .centerCrop()
        .into(imageView)
}