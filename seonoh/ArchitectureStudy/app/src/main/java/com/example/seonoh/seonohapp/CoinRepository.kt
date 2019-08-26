package com.example.seonoh.seonohapp

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import com.example.seonoh.seonohapp.network.BaseResult

interface CoinRepository {
    fun sendMarket(
        listener: BaseResult<List<Market>>
    )
    fun sendCurrentPriceInfo(
        listener: BaseResult<List<CurrentPriceInfoModel>>,
        markets: String
    )
}