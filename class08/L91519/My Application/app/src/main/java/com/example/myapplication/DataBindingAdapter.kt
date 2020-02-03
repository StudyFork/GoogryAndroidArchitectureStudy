package com.example.myapplication

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImage")
fun bindImage(view: ImageView, url: String?) {
    Glide.with(view)
        .load(url)
        .into(view)
}

@BindingAdapter("setRatingStar")
fun bindingRatingStar(view: RatingBar, userRating: Double) {
    view.rating = (userRating / 2).toFloat()
}