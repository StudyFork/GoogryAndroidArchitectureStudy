package com.song2.myapplication.util

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.song2.myapplication.adapter.MovieAdapter
import com.song2.myapplication.source.MovieData

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

@BindingAdapter("setMovieData")
fun setMovieData(view: RecyclerView, movieData: List<MovieData>?) {

    (view.adapter as MovieAdapter).run {
        addItem(movieData)
    }
}

@BindingAdapter("setSystemKeyboard")
fun hideSystemKeyboard(view: View, hasData: Boolean) {

}