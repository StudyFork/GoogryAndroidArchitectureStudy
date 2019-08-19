package com.example.architecturestudy.util

object Util {
    const val THOUSAND = 1_000
    const val MILLION = 1_000_000

    //천 단위로 끊어서 콤마 붙여주는 함수
    fun convertBigNumberToStdString(num: Int): String {
        var resultText = ""
        var leftNumber = num
        var leftNumberStr = num.toString()

        if (num > 1000) {
            while (leftNumber >= 1000) {
                val inputStr = if (leftNumber % 1000 != 0)
                    leftNumberStr.substring(leftNumberStr.length - 3) else "000"

                resultText = "," + inputStr + resultText

                leftNumberStr = leftNumberStr.substring(0, leftNumberStr.length - 3)
                leftNumber /= 1000
            }
        }

        resultText = leftNumberStr + resultText

        return resultText
    }

}