package com.example.kangraemin.util

import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.text.HtmlCompat
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
    textView.text = HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

@BindingAdapter("activateButtonByPresenceOfString")
fun activateButtonByPresenceOfString(button: Button, enteredText: String) {
    if (enteredText.isEmpty()) {
        button.alpha = 0.3f
        button.isEnabled = false
    } else {
        button.alpha = 1f
        button.isEnabled = true
    }
}