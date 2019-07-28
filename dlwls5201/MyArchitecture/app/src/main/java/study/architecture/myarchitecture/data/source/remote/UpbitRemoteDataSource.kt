package study.architecture.myarchitecture.data.source.remote

import io.reactivex.Single
import study.architecture.myarchitecture.data.model.UpbitMarket
import study.architecture.myarchitecture.data.model.UpbitTicker

interface UpbitRemoteDataSource {

    fun getMarkets(): Single<List<UpbitMarket>>

    fun getTickers(key: String): Single<List<UpbitTicker>>
}