package kr.schoolsharing.coinhelper.util

object TextEditor {

    fun makeTradePrice(param: Double) = when {
        param.toInt() > 0 -> String.format("%,d", param.toInt())
        else -> String.format("%,f", param)
    }

    fun makeSignedChangeRate(param: Double) = String.format("%.2f", param * 100)
    fun makeAccTradePrice24h(param: Double) = String.format("%,d", param.toInt())

}