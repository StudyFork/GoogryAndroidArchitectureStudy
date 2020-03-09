package com.example.architecturestudy.ext

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("spannedText")
fun TextView.setSpannedText(s: String) {
    this.text = HtmlCompat.fromHtml(s ?: "", HtmlCompat.FROM_HTML_MODE_COMPACT)
}