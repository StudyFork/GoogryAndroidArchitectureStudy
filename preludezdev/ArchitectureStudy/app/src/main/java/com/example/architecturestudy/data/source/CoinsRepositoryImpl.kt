package com.example.architecturestudy.data.source

import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse
import com.example.architecturestudy.data.source.local.CoinsLocalDataSource
import com.example.architecturestudy.data.source.remote.CoinsRemoteDataSource

class CoinsRepositoryImpl(
    private val coinsRemoteDataSource: CoinsRemoteDataSource,
    private val coinsLocalDataSource: CoinsLocalDataSource
) : CoinsRepository {

    override fun getAllMarket(
        onSuccess: (data: List<CoinMarketResponse>?) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        // 현재는 RemoteDataSource 만 연결해준다.
        coinsRemoteDataSource.getAllMarket(onSuccess, onFail)

        // Local 로 데이터 받아오는 기능이 생기면 추가 구현해야함.
    }

    override fun getCoinTickers(
        market: String,
        onSuccess: (data: List<CoinTickerResponse>?) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        getAllMarket({ coinMarketResponses ->
            if (coinMarketResponses != null) {
                val targetTickers = coinMarketResponses.filter {
                    it.market.split('-')[0] == market
                }.joinToString(separator = ",") { it.market }

                //현재는 RemoteDataSource 만 연결해준다.
                coinsRemoteDataSource.getCoinTickers(targetTickers, onSuccess, onFail)
            }
        }, { onFail(it) })

        // Local 로 데이터 받아오는 기능이 생기면 추가 구현해야함.
    }

}