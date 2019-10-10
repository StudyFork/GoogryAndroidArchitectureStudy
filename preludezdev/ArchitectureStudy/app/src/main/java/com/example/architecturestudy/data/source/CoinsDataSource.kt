package com.example.architecturestudy.data.source

import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse

// RemoteDataSource, LocalDataSource 가 implement 하는 인터페이스
interface CoinsDataSource {

    fun getAllMarket(
        onSuccess: (data: List<CoinMarketResponse>?) -> Unit,
        onFail: (errorMsg: String) -> Unit
    )

    fun getCoinTickers(
        markets: String,
        onSuccess: (data: List<CoinTickerResponse>?) -> Unit,
        onFail: (errorMsg: String) -> Unit
    )

}