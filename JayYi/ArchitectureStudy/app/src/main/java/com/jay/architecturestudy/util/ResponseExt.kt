package com.jay.architecturestudy.util

import okhttp3.Response
import java.nio.charset.Charset

fun Response.peekBody(): String? {
    return body()?.let {
        val source = it.source()
        source.request(Long.MAX_VALUE)
        val buffer = source.buffer()
        buffer.clone().readString(Charset.forName("UTF-8"))
    } ?: null
}