package com.example.seonoh.seonohapp

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import io.reactivex.Single


interface CoinDataSource{

    fun getMarket() : Single<ArrayList<Market>>
    fun getCurrentPriceInfo(marketNameList : String) : Single<ArrayList<CurrentPriceInfoModel>>
}