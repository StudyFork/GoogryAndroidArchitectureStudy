package com.example.architecturestudy.data.source

import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse

class CoinsRepository(
    private val coinsRemoteDataSource: CoinsDataSource,
    private val coinsLocalDataSource: CoinsDataSource
) : CoinsDataSource {

    override fun getAllMarket(
        onSuccess: (data: List<CoinMarketResponse>?) -> Unit,
        onFail: (errorCode: String) -> Unit
    ) {
        // 현재는 RemoteDataSource 만 연결해준다.
        coinsRemoteDataSource.getAllMarket(onSuccess, onFail)

        // Local 로 데이터 받아오는 기능이 생기면 추가 구현해야함.
    }

    override fun getCoinTickers(
        markets: String,
        onSuccess: (data: List<CoinTickerResponse>?) -> Unit,
        onFail: (errorCode: String) -> Unit
    ) {
        //현재는 RemoteDataSource 만 연결해준다.
        coinsRemoteDataSource.getCoinTickers(markets, onSuccess, onFail)

        // Local 로 데이터 받아오는 기능이 생기면 추가 구현해야함.
    }

    companion object {
        private var INSTANCE: CoinsRepository? = null

        fun getInstance(
            coinsRemoteDataSource: CoinsDataSource,
            coinsLocalDataSource: CoinsDataSource
        ): CoinsRepository {
            return INSTANCE ?: CoinsRepository(coinsRemoteDataSource, coinsLocalDataSource).apply { INSTANCE = this }
        }
    }

}