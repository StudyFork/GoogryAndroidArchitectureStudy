package com.buddman1208.architecturestudy.utils

fun String.removeHtmlBoldTags(): String {
    return this
        .replace("<b>", "")
        .replace("</b>", "")
}