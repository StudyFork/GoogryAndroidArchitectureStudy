package com.example.myapplication.util

import android.annotation.TargetApi
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.databinding.BindingAdapter


@TargetApi(Build.VERSION_CODES.N)
@BindingAdapter("android:htmlText")
fun androidHtmlText(textView: TextView, htmlText: String?) {
    if (htmlText == null) return
    val result: Spanned = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
    textView.text = result
}