package com.jay.architecturestudy.util

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter

@BindingAdapter("loadUrl")
fun WebView.loadUrl(url: String) {
    loadUrl(url)
}