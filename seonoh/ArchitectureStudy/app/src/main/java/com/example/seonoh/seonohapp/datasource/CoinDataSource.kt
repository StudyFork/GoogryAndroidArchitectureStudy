package com.example.seonoh.seonohapp.datasource

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import io.reactivex.Single


interface CoinDataSource {

    fun getMarket(): Single<List<Market>>
    fun getCurrentPriceInfo(marketNameList: String): Single<List<CurrentPriceInfoModel>>
}