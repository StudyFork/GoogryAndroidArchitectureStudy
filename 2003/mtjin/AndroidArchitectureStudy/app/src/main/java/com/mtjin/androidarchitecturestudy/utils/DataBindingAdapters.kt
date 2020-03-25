package com.mtjin.androidarchitecturestudy.utils

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mtjin.androidarchitecturestudy.R


@BindingAdapter("htmlText")
fun setHtmlText(textView: TextView, html: String) {
    HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("urlImage")
fun setUrlImage(imageView: ImageView, url: String) {
    Glide.with(imageView).load(url)
        .placeholder(R.drawable.ic_default)
        .into(imageView)
}

@BindingAdapter("movieRating")
fun setMovieRating(ratingBar: RatingBar, score: String) {
    ratingBar.rating = (score.toFloatOrNull() ?: 0f) / 2
}

/*
확장함수 사용 필기용입니다.
@BindingAdapter("app:movieRating")
fun RatingBar.setMovieRating(score: String) {
    rating = (score.toFloatOrNull() ?: 0f) / 2
}
*/


