package com.example.architecturestudy.util

import java.text.DecimalFormat

object Util {
    const val THOUSAND = 1_000
    const val MILLION = 1_000_000

    //천 단위로 끊어서 콤마 붙여주는 함수
    fun convertBigNumberToStdString(num: Int): String {
        val df = DecimalFormat("#,###")

        return df.format(num)
    }

}