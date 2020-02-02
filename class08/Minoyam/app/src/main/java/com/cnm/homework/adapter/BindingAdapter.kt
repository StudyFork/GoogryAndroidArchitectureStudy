package com.cnm.homework.adapter

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:bindImage")
fun bindImage(imageView: ImageView, imageUri: String) {
    Glide.with(imageView.context).load(imageUri).into(imageView)
}

@BindingAdapter("bind:bindRating")
fun bindRating(ratingBar: RatingBar, rating: Float) {
    ratingBar.rating = rating / 2
}