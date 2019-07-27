package kr.schoolsharing.coinhelper.model

object UpbitList {
    val krwList: MutableList<UpbitItem> = ArrayList()
    val btcList: MutableList<UpbitItem> = ArrayList()
    val ethList: MutableList<UpbitItem> = ArrayList()
    val usdtList: MutableList<UpbitItem> = ArrayList()

    fun getListFromName(param: String) = when (param) {
        "KRW" -> krwList
        "BTC" -> btcList
        "ETH" -> ethList
        else -> usdtList
    }
}
