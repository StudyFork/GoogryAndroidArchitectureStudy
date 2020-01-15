package com.example.handnew04.adapter

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:bindImage")
fun bindImage(imageView: ImageView, imageUri: String) {
    Glide.with(imageView.context).load(imageUri).into(imageView)
}

@BindingAdapter("bind:bindRatingString")
fun bindRatingString(ratingBar: RatingBar, rating: String) {
    ratingBar.rating = rating.toFloat()
}

@BindingAdapter("android:text")
fun setText(textView: TextView, text: String) {
    val htmlText: String
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        htmlText = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        htmlText = Html.fromHtml(text).toString()
    }

    textView.setText(htmlText)
}