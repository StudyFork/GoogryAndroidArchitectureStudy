package com.example.handnew04.adapter

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:bindImage")
fun bindImage(imageView: ImageView, imageUri: String) {
    Glide.with(imageView.context).load(imageUri).into(imageView)
}

@BindingAdapter("bind:bindRatingString")
fun bindRatingString(ratingBar: RatingBar, rating: String) {
    ratingBar.rating = rating.toFloat()
}