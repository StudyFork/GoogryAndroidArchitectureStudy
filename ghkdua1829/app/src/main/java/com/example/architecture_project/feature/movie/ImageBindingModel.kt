package com.example.architecture_project.feature.movie

import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageRes")
fun ImageView.ImageRes(url: String) {
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("ratingStar")
fun RatingBar.starNum(star: Float) {
    rating = star / 2
}

@BindingAdapter("htmlText")
fun TextView.fromHtml(title: String) {
    text = Html.fromHtml(title)
}