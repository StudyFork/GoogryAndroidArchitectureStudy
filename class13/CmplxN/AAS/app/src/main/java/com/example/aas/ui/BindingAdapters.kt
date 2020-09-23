package com.example.aas.ui

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit

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

typealias PresenterMediator = () -> Unit

@BindingAdapter("throttledClick")
fun View.setThrottleClick(function: PresenterMediator) {
    RxView.clicks(this)
        .throttleFirst(1000L, TimeUnit.MILLISECONDS)
        .subscribe { function() }
}