package study.architecture.myarchitecture.data.source

import io.reactivex.Single
import study.architecture.myarchitecture.data.model.UpbitMarket

interface UpbitDataSource {

    fun getMarkets(): Single<List<UpbitMarket>>

}