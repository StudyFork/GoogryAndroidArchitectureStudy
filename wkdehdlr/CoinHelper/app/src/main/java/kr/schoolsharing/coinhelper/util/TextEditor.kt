package kr.schoolsharing.coinhelper.util

object TextEditor {

    fun makeTradePrice(param: Double) = when {
        param.toInt() > 0 -> String.format("%,d", param.toInt())
        else -> String.format("%,f", param)
    }

    fun makeSignedChangeRate(param: Double) = String.format("%.2f", param * 100)
    fun makeAccTradePrice24h(param: Double) = when {
        param > 10000000 -> (param / 1000000).toInt().toString() + "M"
        param > 100000 -> (param / 1000).toInt().toString() + "K"
        param > 1000 -> (param / 1000000).toInt().toString()
        else -> String.format("%.3f", param)
    }
    fun splitString(param: String, idx: Int) = param.split("-")[idx]


}