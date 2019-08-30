package com.example.seonoh.seonohapp

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import io.reactivex.Single

interface CoinRepository {
    fun sendMarket() : Single<List<Market>>
    fun sendCurrentPriceInfo(
        markets: String?
    ) : Single<List<CurrentPriceInfoModel>>
}