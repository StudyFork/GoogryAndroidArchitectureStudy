package my.gong.studygong.data.source.upbit

import my.gong.studygong.data.model.Ticker

interface IUpbitDataSource {
//
//    fun getMarket(
//        success: (String) -> Unit
//    )

    fun getTickers(
        success: (List<Ticker>) -> Unit,
        fail: (String) -> Unit
    )

}