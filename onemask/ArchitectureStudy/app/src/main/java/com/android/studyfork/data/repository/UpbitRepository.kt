package com.android.studyfork.data.repository

import com.android.studyfork.network.api.UpbitApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * created by onemask
 */
class UpbitRepository(
    private val api: UpbitApi
) : UpbitDataSource {

    override fun getMarketAll() =
        api.getMarketAll()
            .flatMap { markets ->
                val marketName = markets
                    .groupBy {
                        val idx = it.market.indexOf("-")
                        it.market.substring(0, idx)
                    }
                Single.just(marketName)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    override fun getTickers(market: String) =
        api.getTickers(market)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    companion object {
        private var INSTANCE: UpbitRepository? = null

        @JvmStatic
        fun getInstance(api: UpbitApi): UpbitDataSource {
            return INSTANCE ?: UpbitRepository(api).apply {
                INSTANCE = this
            }
        }

        @JvmStatic
        fun destroruInstance() {
            INSTANCE = null
        }
    }

}

