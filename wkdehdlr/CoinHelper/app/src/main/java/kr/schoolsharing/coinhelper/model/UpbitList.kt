package kr.schoolsharing.coinhelper.model

data class UpbitList(
    val krwList: MutableList<UpbitItem>,
    val btcList: MutableList<UpbitItem>,
    val ethList: MutableList<UpbitItem>,
    val usdtList: MutableList<UpbitItem>
)
