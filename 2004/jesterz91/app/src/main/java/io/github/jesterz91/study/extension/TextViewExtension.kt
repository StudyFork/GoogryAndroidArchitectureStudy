package io.github.jesterz91.study.extension

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("fromHtml")
fun TextView.fromHtml(text: String?) {
    text?.let {
        setText(HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_COMPACT))
    }
}