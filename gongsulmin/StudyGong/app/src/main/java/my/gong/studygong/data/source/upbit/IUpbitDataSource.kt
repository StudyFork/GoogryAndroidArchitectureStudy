package my.gong.studygong.data.source.upbit

import my.gong.studygong.data.model.response.UpbitMarketResponse
import my.gong.studygong.data.model.response.UpbitTickerResponse

interface IUpbitDataSource {
    fun getMarket(
        success: (List<UpbitMarketResponse>) -> Unit ,
        fail: (String) -> Unit
    )

    fun getTickers(
        market: String,
        success: (List<UpbitTickerResponse>) -> Unit ,
        fail: (String) -> Unit
    )

}