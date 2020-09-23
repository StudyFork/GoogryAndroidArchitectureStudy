package com.example.aas.ui

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("htmlText")
fun TextView.setHtmlTest(text: String) {
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
    else
        Html.fromHtml(text)
}

@BindingAdapter("loadImageFromUri")
fun ImageView.loadImageFromUrl(uri: String) {
    Glide.with(this)
        .load(uri)
        .centerCrop()
        .into(this)
}
