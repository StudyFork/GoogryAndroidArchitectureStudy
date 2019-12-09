package com.example.seonoh.seonohapp.datasource

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import com.example.seonoh.seonohapp.network.Api
import io.reactivex.Single
import javax.inject.Inject

class CoinDataSourceImpl @Inject constructor(
    private val coinApi: Api
) : CoinDataSource {


    override fun getMarket(): Single<List<Market>> = coinApi.getMarketAll()

    override fun getCurrentPriceInfo(marketNameList: String): Single<List<CurrentPriceInfoModel>> =
        coinApi.getCurrentPriceInfo(marketNameList)
}