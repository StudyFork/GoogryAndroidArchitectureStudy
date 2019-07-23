package study.architecture.model.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import study.architecture.ui.MainFragment
import study.architecture.model.datasource.RemoteDataSource
import study.architecture.model.vo.Ticker

object Repository {

    fun getMarketList(index: MainFragment.FragIndex): Single<String> =
        RemoteDataSource.paresMarketList()
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.filter { filterData -> filterData.market.startsWith(index.name) }
                    .joinToString(",") { separateData -> separateData.market }
            }


    fun getTickerList(listName: String): Single<MutableList<Ticker>> =
        RemoteDataSource.parseTickerList(listName)


}