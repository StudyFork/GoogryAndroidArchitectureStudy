package com.song2.myapplication.util

import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
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
    view.rating = (userRating!! / 2).toFloat()
}

@BindingAdapter("setTitle")
fun setMovieTitle(view: TextView, movieTitle: String?) {
    view.text = Html.fromHtml(movieTitle)
}
