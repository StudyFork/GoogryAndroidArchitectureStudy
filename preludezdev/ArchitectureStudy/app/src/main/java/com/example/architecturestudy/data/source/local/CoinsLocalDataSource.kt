package com.example.architecturestudy.data.source.local

import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse
import com.example.architecturestudy.data.source.CoinsDataSource

class CoinsLocalDataSource private constructor() : CoinsDataSource {
    override fun getAllMarket(
        onSuccess: (data: List<CoinMarketResponse>?) -> Unit,
        onFail: (errorCode: String) -> Unit
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCoinTickers(
        markets: String,
        onSuccess: (data: List<CoinTickerResponse>?) -> Unit,
        onFail: (errorCode: String) -> Unit
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private var INSTANCE: CoinsLocalDataSource? = null

        fun getInstance(): CoinsLocalDataSource {
            return INSTANCE ?: CoinsLocalDataSource().apply { INSTANCE = this }
        }
    }

}