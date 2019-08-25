package com.example.seonoh.seonohapp

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import com.example.seonoh.seonohapp.network.CoinRequest

interface CoinRepository {
    fun sendMarket(listener: CoinRequest.BaseResult<ArrayList<Market>>)
    fun sendCurrentPriceInfo(listener: CoinRequest.BaseResult<ArrayList<CurrentPriceInfoModel>>, markets: String)
}