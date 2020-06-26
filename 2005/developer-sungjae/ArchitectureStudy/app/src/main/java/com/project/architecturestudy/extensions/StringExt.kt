package com.project.architecturestudy.extensions

import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup

fun String.parseHtmlTag(): String {
    val strWithoutSign = StringEscapeUtils.unescapeHtml4(this)
    return Jsoup.parse(strWithoutSign).text()
}