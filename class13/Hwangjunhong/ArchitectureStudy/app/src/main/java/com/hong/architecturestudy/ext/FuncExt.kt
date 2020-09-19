package com.hong.architecturestudy.ext

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("toHtml")
fun TextView.toHtml(html: String) {
    this.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("movieRating")
fun RatingBar.setMovieRating(score: String) {
    this.rating = (score.toFloatOrNull() ?: 0f) / 2
}

fun hideKeyboard(context: Context, view: View) {
    val inputMethod =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethod.hideSoftInputFromWindow(view.windowToken, 0)
}

