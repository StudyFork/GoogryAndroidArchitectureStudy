package com.jay.architecturestudy.util

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("android:text")
fun TextView.setText(title: String) {
    text = HtmlCompat.fromHtml(title, HtmlCompat.FROM_HTML_MODE_COMPACT)
}