package com.lllccww.studyforkproject.ui.extension

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("htmlTextChange")
fun TextView.htmlTextChange(html: String) {
    this.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
}