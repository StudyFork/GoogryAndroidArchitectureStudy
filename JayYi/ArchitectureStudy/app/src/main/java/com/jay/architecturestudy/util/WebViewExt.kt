package com.jay.architecturestudy.util

import android.webkit.WebView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadUrl")
fun WebView.loadUrl(url: String) {
    loadUrl(url)
}