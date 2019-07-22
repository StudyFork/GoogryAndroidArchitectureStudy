package com.android.studyfork.data.repository

import com.android.studyfork.network.remote.model.UpbitMarketResponse
import com.android.studyfork.network.remote.model.UpbitTickerResponse
import io.reactivex.Single

/**
 * created by onemask
 */
interface UpbitDatasource {
    fun getMarketAll(): Single<Map<String, List<UpbitMarketResponse>>>
    fun getTikcers(margket: String): Single<List<UpbitTickerResponse>>
}