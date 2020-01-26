@file:Suppress("DEPRECATION", "UNCHECKED_CAST")

package com.example.architecture_project.feature.movie

import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecture_project.data.model.Movie

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

@BindingAdapter("setData")
fun RecyclerView.setData(movieData: List<Any>?) {
    when (movieData) {
        null -> {
        }
        else -> {
            if (movieData.isNotEmpty())
                (this.adapter as MovieAdapter).setMovieItemList(movieData as List<Movie>)
        }
    }
}