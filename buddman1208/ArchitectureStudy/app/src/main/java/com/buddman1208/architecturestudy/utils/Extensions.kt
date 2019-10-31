package com.buddman1208.architecturestudy.utils

fun String.removeHtmlTags(): String {
    return this
        .replace("<b>", "")
        .replace("</b>", "")
}