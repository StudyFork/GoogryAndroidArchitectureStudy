package io.github.sooakim.util.ext

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatter(pattern: String, isLocal: Boolean = false): SimpleDateFormat {
    val locale = if (isLocal) Locale.getDefault() else Locale.ENGLISH
    return SimpleDateFormat(pattern, locale)
}

fun Date.formatWith(pattern: String, isLocal: Boolean = false): String {
    return formatter(pattern, isLocal).format(this)
}