package com.sangjin.newproject.utils

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sangjin.newproject.data.model.Movie

@BindingAdapter("htmlToSpanned")
fun TextView.htmlToSpanned(item: Movie) {
    item?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            text = Html.fromHtml(it.title, Html.FROM_HTML_MODE_LEGACY)
        } else {
            text = Html.fromHtml(it.title)
        }
    }
}