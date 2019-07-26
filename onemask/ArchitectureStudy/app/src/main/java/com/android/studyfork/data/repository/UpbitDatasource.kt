package com.android.studyfork.data.repository

import com.android.studyfork.network.remote.model.UpbitMarketResponse
import com.android.studyfork.network.remote.model.UpbitTickerResponse
import io.reactivex.Single

/**
 * created by onemask
 */
interface UpbitDataSource {
    fun getMarketAll(): Single<Map<String, List<UpbitMarketResponse>>>
    fun getTickers(market: String): Single<List<UpbitTickerResponse>>
}