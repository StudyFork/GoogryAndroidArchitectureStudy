package com.example.kangraemin.util

import android.os.Build
import android.text.Html
import android.text.Spanned

object Utils {
    fun fromHtml(input: String): Spanned {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            return Html.fromHtml(input)
        }
        return Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY)
    }
}