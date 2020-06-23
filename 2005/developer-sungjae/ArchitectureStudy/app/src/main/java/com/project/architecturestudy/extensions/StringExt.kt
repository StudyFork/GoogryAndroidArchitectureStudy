package com.project.architecturestudy.extensions

import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup

fun String.parseHTMLTag(): String {
    val strWithoutSign = StringEscapeUtils.unescapeHtml4(this)
    return Jsoup.parse(strWithoutSign).text()
}