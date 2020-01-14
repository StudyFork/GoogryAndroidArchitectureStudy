package com.hansung.firstproject.extension

import android.annotation.TargetApi
import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["loadUrl"])
fun ImageView.loadUrl(url: String) =
    Glide.with(context)
        .load(url)
        .apply(RequestOptions().override(300, 450))
        .apply(RequestOptions.centerCropTransform())
        .into(this)

@BindingAdapter(value = ["setRating"])
fun RatingBar.setStrRating(userRating: String) {
    this.rating = userRating.toFloat() / 2
}

@TargetApi(Build.VERSION_CODES.N)
@BindingAdapter(value = ["setTitle"])
fun TextView.setStrTitle(title: String) =
    Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY)!!