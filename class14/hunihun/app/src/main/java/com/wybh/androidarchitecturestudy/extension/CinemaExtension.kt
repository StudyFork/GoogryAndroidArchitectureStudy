package com.wybh.androidarchitecturestudy.extension

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.wybh.androidarchitecturestudy.R

@BindingAdapter("bind_image")
fun bindImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .into(view)
}

@BindingAdapter("bind_title")
fun bindTitle(view: TextView, title: String) {
    view.text = String.format("제목 : %s", title)
}

@BindingAdapter("bind_date")
fun bindDate(view: TextView, date: String) {
    view.text = String.format("출시 : %s", date)
}

@BindingAdapter("bind_rating")
fun bindRating(view: TextView, rating: String) {
    view.text = String.format("평점 : %s", rating)
}