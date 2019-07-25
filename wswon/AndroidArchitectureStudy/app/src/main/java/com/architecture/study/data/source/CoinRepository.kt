package com.architecture.study.data.source

import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse
import com.architecture.study.network.request.UpbitRequest
import com.architecture.study.network.request.UpbitRequestListener

class CoinRepository : CoinDataSource{

    override fun getMarketList(listener: UpbitRequestListener<MarketResponse>) {
        UpbitRequest().apply {
            getMarketList(object : UpbitRequestListener<MarketResponse> {
                override fun onSucess(dataList: List<MarketResponse>) {
                    listener.onSucess(dataList)
                }

                override fun onEmpty(str: String) {
                    listener.onEmpty(str)
                }

                override fun onFailure(str: String) {
                    listener.onFailure(str)
                }
            })
        }
    }

    override fun getTickerList(marketNames: String, listener: UpbitRequestListener<TickerResponse>) {
        UpbitRequest().apply {
            getTickerList(marketNames, object :
                UpbitRequestListener<TickerResponse> {
                override fun onSucess(dataList: List<TickerResponse>) {
                    listener.onSucess(dataList)
                }

                override fun onEmpty(str: String) {
                    listener.onEmpty(str)
                }

                override fun onFailure(str: String) {
                    listener.onFailure(str)
                }
            })
        }
    }
}