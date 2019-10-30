package study.architecture.data.datasource.remote

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import study.architecture.data.entity.Market
import study.architecture.data.entity.Ticker

/**
 * Data 관리하는 비즈니스로직이 담긴 클래스
 */
class RemoteDataSourceImpl(private val api: UpbitApi) : RemoteDataSource {

    override fun getMarkets(): Single<List<Market>> =
        api.getMarkets()
            .subscribeOn(Schedulers.io())

    override fun getTickers(markets: String): Single<MutableList<Ticker>> =
        api.getTickers(markets)
            .subscribeOn(Schedulers.io())

}