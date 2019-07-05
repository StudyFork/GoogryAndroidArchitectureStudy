package com.studyfirstproject.util

import java.text.DecimalFormat

object NumberFormatUtil {
    private val insertCommaFormat = DecimalFormat("#,###")
    private const val standardNum = 1_000_000 // 생략 기준

    // 백만 이하 숫자는 생략
    fun skipUnderMillions(num: Double): String =
        if (num < standardNum) {
            insertComma(num)
        } else {
            insertComma(num / standardNum) + "M"
        }

    // 세자리 단위로 끊어 , 삽입
    fun insertComma(num: Double): String = insertCommaFormat.format(num.toLong())

    // 백분율 변환
    fun getPercent(num: Double): String {
        val (type, percent) =
            when {
                num < 0 -> "▼" to -num
                num > 0 -> "▲" to num
                else -> "" to num
            }

        return String.format("%s %.2f%%", type, percent * 100)
    }
}