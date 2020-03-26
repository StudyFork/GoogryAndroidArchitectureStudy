package com.example.kangraemin.util

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun setImageFromUrl(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .centerCrop()
        .into(view)
}

@BindingAdapter("rating")
fun setRating(ratingBar: RatingBar, rating: String) {
    ratingBar.rating = (rating.toFloatOrNull() ?: 0f) / 2
}

@BindingAdapter("fromHtml")
fun transferFromHtml(textView: TextView, htmlString: String) {
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
        textView.text = Html.fromHtml(htmlString)
    } else {
        textView.text = Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY)
    }
}