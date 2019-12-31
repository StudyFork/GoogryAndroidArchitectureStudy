package com.example.kotlinapplication.extension

import android.os.Build
import android.text.Html

fun String.getHtmlText() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(this)
    }

