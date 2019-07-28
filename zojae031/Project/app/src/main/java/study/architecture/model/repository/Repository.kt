package study.architecture.model.repository

import io.reactivex.Flowable
import io.reactivex.Single
import study.architecture.model.datasource.RemoteDataSource
import study.architecture.model.vo.ProcessingTicker
import study.architecture.ui.coinjob.CoinFragment
import study.architecture.util.TextUtil
import java.util.concurrent.TimeUnit

object Repository {

    fun getMarketList(index: CoinFragment.FragIndex): Single<String> =
        RemoteDataSource.getMarkets()
            .map { list ->
                list.filter { filterData -> filterData.market.startsWith(index.name) }
                    .joinToString(",") { separateData -> separateData.market }
            }


    fun getTickerList(listName: String): Flowable<List<ProcessingTicker>> =
        RemoteDataSource.getTickers(listName)
            .repeatWhen { t -> t.delay(8, TimeUnit.SECONDS) }
            .map { list ->
                list.map { data ->
                    ProcessingTicker(
                        TextUtil.getMarketName(data.market),
                        TextUtil.getTradePrice(data.tradePrice),
                        TextUtil.getChangeRate(data.signedChangeRate),
                        TextUtil.getAccTradePrice24h(data.accTradePrice24h),
                        TextUtil.getColorState(data.signedChangeRate)
                    )
                }
            }


}