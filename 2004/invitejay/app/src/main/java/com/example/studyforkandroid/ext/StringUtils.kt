package com.example.studyforkandroid.ext

import android.os.Build
import android.text.Html

fun String.htmlToString(): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    }
    return Html.fromHtml(this).toString()
}