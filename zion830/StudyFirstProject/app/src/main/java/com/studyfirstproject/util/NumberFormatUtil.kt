package com.studyfirstproject.util

import java.text.DecimalFormat

object NumberFormatUtil {
    val df = DecimalFormat("###,###")

    // 백만 이하 숫자는 생략
    fun skipUnderMillions(num: Double): String = if (num < 1000000) {
        insertComma(num)
    } else {
        insertComma(num / 1000000) + "M"
    }

    // 세자리 단위로 끊어 , 삽입
    fun insertComma(num: Number): String = df.format(num).toString()

    // 백분율 변환
    fun getPercent(num: Double): String {
        var type = ""
        var percent = num * 100

        if (num < 0) {
            type = "▼"
            percent = 0 - percent
        } else if (num > 0) {
            type = "▲"
        }

        return String.format("%s %.2f%%", type, percent)
    }
}