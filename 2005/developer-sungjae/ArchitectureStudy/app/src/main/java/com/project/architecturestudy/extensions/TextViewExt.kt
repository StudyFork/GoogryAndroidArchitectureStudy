package com.project.architecturestudy.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("parseHtml")
fun TextView.parseHtml(text: String?) {
    this.text = text?.parseHtmlTag()
}