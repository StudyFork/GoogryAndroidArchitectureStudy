package com.example.myproject.extension

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("imageUrl")
fun ImageView.setLoadUrl(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)}

@BindingAdapter("rating")
fun RatingBar.setRating(rating: Double){
    val fRating = rating.toFloat()
    setRating(fRating/2)
}

@BindingAdapter("htmlText")
fun TextView.setHtmlText(text: String) {
    this.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

