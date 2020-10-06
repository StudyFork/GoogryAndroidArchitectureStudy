package com.hong.architecturestudy.ext

import android.widget.RatingBar
import androidx.databinding.BindingAdapter

@BindingAdapter("movieRating")
fun RatingBar.setMovieRating(score: String) {
    this.rating = (score.toFloatOrNull() ?: 0f) / 2
}
