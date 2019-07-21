package kr.schoolsharing.coinhelper.data

data class UpbitItem(
    val name: String,
    val current: String,
    val change: String,
    val signedChangeRate: String,
    val volume: String
)