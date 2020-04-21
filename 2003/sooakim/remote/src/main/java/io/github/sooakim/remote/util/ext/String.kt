package io.github.sooakim.remote.util.ext

import androidx.core.text.HtmlCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.formatter(pattern: String, isLocal: Boolean = false): SimpleDateFormat {
    val locale = if (isLocal) Locale.getDefault() else Locale.ENGLISH
    return SimpleDateFormat(pattern, locale)
}

fun String.toDateWith(pattern: String, isLocal: Boolean = false): Date? {
    return formatter(pattern, isLocal).runCatching {
        parse(this@toDateWith)
    }.getOrNull()
}

fun String.toPlainFromHtml(): String {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
}