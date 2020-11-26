package com.example.androidarchitecturestudy

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadUrl(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .into(this)
}

@BindingAdapter("title")
fun TextView.setTitleText(title: String) {
    this.text = title.replace("<b>", "").replace("</b>", "")
}

@BindingAdapter("director")
fun TextView.setDirector(director: String) {
    this.text = director.replace("|", "")
}
