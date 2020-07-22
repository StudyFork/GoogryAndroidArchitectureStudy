package com.tsdev.domain.ext

import androidx.core.text.HtmlCompat

fun String.htmlConvert() =
    HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)