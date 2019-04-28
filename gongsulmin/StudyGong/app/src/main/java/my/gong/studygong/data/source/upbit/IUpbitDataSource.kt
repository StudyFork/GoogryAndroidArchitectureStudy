package my.gong.studygong.data.source.upbit

import my.gong.studygong.data.model.response.UpbitTickerResponse

interface IUpbitDataSource {
//
//    fun getMarket(
//        success: (String) -> Unit
//    )

    fun getTickers(
        success: (List<UpbitTickerResponse>) -> Unit ,
        fail: (String) -> Unit
    )

}