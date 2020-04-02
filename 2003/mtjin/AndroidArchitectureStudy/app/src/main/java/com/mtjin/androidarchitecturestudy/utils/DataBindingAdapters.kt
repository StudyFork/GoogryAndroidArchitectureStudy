package com.mtjin.androidarchitecturestudy.utils

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.data.search.Movie
import com.mtjin.androidarchitecturestudy.ui.search.MovieAdapter


@BindingAdapter("htmlText")
fun TextView.setHtmlText(html: String) {
    text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("urlImage")
fun ImageView.setUrlImage(url: String) {
    Glide.with(this).load(url)
        .placeholder(R.drawable.ic_default)
        .into(this)
}

@BindingAdapter("movieRating")
fun RatingBar.setMovieRating(score: String) {
    rating = (score.toFloatOrNull() ?: 0f) / 2
}

@BindingAdapter("setItems", "loadMore")
fun RecyclerView.setAdapterItems(items: List<Movie>?, loadMore: Boolean) {
    with((adapter as MovieAdapter)) {
        if (!loadMore) {
            this.clear()
        }
        items?.let { this.addItems(it) }
    }
}


