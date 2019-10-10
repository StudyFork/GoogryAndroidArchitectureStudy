package com.example.architecturestudy.data.source

import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse

//CoinsRepositoryImpl, RemoteDataSource, LocalDataSource 가 implement 하는 인터페이스
interface CoinsRepository {

    fun getAllMarket(
        onSuccess: (data: List<CoinMarketResponse>?) -> Unit,
        onFail: (t: Throwable) -> Unit
    )

    fun getCoinTickers(
        market: String,
        onSuccess: (data: List<CoinTickerResponse>?) -> Unit,
        onFail: (t: Throwable) -> Unit
    )

}