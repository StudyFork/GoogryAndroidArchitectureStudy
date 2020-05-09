package com.eunice.eunicehong.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("app:formattedText")
fun formattedText(textView: TextView, text: String) {
    val formatted = text.split("|")
        .map { it.trim() }
        .filter { !it.isBlank() }
        .joinToString()

    textView.text = formatted.parseHtml()
}
