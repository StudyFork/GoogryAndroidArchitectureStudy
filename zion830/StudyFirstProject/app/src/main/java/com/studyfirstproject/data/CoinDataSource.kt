package com.studyfirstproject.data

import com.studyfirstproject.data.model.TickerModel

interface CoinDataSource {

    fun getAllMarkets(
        success: (String) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun getCoinData(
        markets: String,
        success: (List<TickerModel>) -> Unit,
        failed: (String, String?) -> Unit
    )
}