package com.practice.achitecture.myproject.utils

class MyStringUtil {
    companion object {
        fun removeHtmlTags(text: String?): String? {
            return text?.let {
                it.replace("<.*?>".toRegex(), "")
                    .replace("&.*?;".toRegex(), "")
            }
        }
    }
}