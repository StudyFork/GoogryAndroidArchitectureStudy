package com.example.architecturestudy.ui

import android.content.Intent
import android.view.View
import com.example.architecturestudy.ui.WebViewActivity.Companion.EXTRA_URL
fun View.startWebView(url : String) {
    Intent(context, WebViewActivity::class.java).apply {
        putExtra(EXTRA_URL, url)
    }.run {
        context.startActivity(this)
    }
}