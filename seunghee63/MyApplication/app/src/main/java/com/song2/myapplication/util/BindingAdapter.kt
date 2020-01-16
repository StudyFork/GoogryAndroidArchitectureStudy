package com.song2.myapplication.util

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImage")
fun setPosterImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}

@BindingAdapter("setRating")
fun setRatingValue(view: RatingBar, userRating: Double?) {
    //view.rating = (userRating!! / 2).toFloat()
}