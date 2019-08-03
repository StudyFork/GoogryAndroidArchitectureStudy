package study.architecture.model.repository

import io.reactivex.Flowable
import io.reactivex.Single
import study.architecture.model.datasource.RemoteDataSource
import study.architecture.model.vo.Market
import study.architecture.model.vo.Ticker
import java.util.concurrent.TimeUnit

object RepositoryImpl : Repository {

    override fun getMarketList(): Single<List<Market>> =
        RemoteDataSource.getMarkets()


    override fun getTickerList(listName: String): Flowable<MutableList<Ticker>> =
        RemoteDataSource.getTickers(listName)
            .repeatWhen { t -> t.delay(8, TimeUnit.SECONDS) }


}