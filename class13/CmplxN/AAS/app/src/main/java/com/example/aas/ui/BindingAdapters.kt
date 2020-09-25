package com.example.aas.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit

@BindingAdapter("htmlText")
fun TextView.setHtmlTest(text: String) {
    this.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("loadImageFromUri")
fun ImageView.loadImageFromUrl(uri: String) {
    Glide.with(this)
        .load(uri)
        .centerCrop()
        .into(this)
}

@BindingAdapter("throttledClick")
fun View.setThrottleClick(onClickListener: View.OnClickListener?) {
    onClickListener?.let {
        RxView.clicks(this)
            .throttleFirst(1000L, TimeUnit.MILLISECONDS)
            .subscribe { _ -> it.onClick(this) }
    }
}