package study.architecture.model.repository

import io.reactivex.Single
import study.architecture.model.datasource.RemoteDataSource
import study.architecture.model.vo.Ticker
import study.architecture.ui.MainFragment

object Repository {

    fun getMarketList(index: MainFragment.FragIndex): Single<String> =
        RemoteDataSource.getMarkets()
            .map { list ->
                list.filter { filterData -> filterData.market.startsWith(index.name) }
                    .joinToString(",") { separateData -> separateData.market }
            }


    fun getTickerList(listName: String): Single<MutableList<Ticker>> =
        RemoteDataSource.getTickers(listName)


}