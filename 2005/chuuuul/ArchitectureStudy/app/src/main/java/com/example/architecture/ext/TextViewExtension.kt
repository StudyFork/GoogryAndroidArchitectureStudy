package com.example.architecture.ext

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup


@BindingAdapter("android:renderHtml")
fun TextView.renderHtml(html: String?) {
    if (html.isNullOrBlank()) {
        return
    }

    val escapedHtml = StringEscapeUtils.unescapeHtml4(html)
    text = Jsoup.parse(escapedHtml).text()
}

@BindingAdapter("android:onEnterClick")
fun EditText.setOnEnterClick(onSearchClick: View.OnClickListener) {
    setOnEditorActionListener { _, actionId, _ ->

        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            onSearchClick.onClick(this)
            true
        } else {
            false
        }
    }
}


