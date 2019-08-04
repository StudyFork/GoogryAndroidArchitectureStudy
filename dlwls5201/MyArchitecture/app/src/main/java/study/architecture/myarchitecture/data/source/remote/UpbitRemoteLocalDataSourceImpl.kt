package study.architecture.myarchitecture.data.source.remote

import io.reactivex.Single
import study.architecture.myarchitecture.data.model.UpbitMarket
import study.architecture.myarchitecture.data.model.UpbitTicker

class UpbitRemoteLocalDataSourceImpl(
    private val api: UpbitApi
) : UpbitRemoteDataSource {

    override fun getMarkets(): Single<List<UpbitMarket>> = api.getMarkets()

    override fun getTickers(key: String): Single<List<UpbitTicker>> = api.getTickers(key)
}