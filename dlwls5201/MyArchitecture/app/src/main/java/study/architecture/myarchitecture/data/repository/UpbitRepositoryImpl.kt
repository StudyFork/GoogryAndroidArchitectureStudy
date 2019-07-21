package study.architecture.myarchitecture.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import study.architecture.myarchitecture.data.model.UpbitMarket
import study.architecture.myarchitecture.data.model.UpbitTicker
import study.architecture.myarchitecture.data.source.local.UpbitDao
import study.architecture.myarchitecture.data.source.remote.UpbitApi
import java.util.regex.Pattern

class UpbitRepositoryImpl(
    private val api: UpbitApi,
    private val dao: UpbitDao,
    private val isOnline: Boolean
) : UpbitRepository {

    override fun getGroupedMarkets(): Single<Map<String, List<UpbitMarket>>> {
        //Timber.d("isOnline : $isOnline")
        return if (isOnline) {
            api.getMarkets()
                .flatMap { markets ->

                    dao.clearMarkets()

                    for (market in markets) {
                        //Timber.d("api market 저장 -> $market")
                        dao.insertMarket(market)
                    }

                    val pattern = Pattern.compile("^([a-zA-Z]*)-([a-zA-Z]*)$")

                    val groupMarket = markets
                        .filter { pattern.matcher(it.market).find() }
                        .groupBy {
                            val idx = it.market.indexOf("-")
                            it.market.substring(0, idx)
                        }

                    Single.just(groupMarket)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        } else {
            dao.getMarkets()
                .flatMap { markets ->

                    val pattern = Pattern.compile("^([a-zA-Z]*)-([a-zA-Z]*)$")

                    val groupMarket = markets
                        .filter { pattern.matcher(it.market).find() }
                        .groupBy {
                            val idx = it.market.indexOf("-")
                            it.market.substring(0, idx)
                        }

                    Single.just(groupMarket)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    override fun getTickers(markets: String): Single<List<UpbitTicker>> {
        //Timber.e("isOnline : $isOnline , markets : $markets")
        return if (isOnline) {
            api.getTickers(markets)
                .flatMap { tickers ->

                    val idx = markets.indexOf("-")
                    val marketKey = markets.substring(0, idx)
                    //Timber.e("clear marketKey : $marketKey")

                    dao.clearTickers("$marketKey%")

                    for (ticker in tickers) {
                        //Timber.d("api ticker 저장 -> $ticker")
                        dao.insertTicker(ticker)
                    }

                    Single.just(tickers)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        } else {
            val idx = markets.indexOf("-")
            val marketKey = markets.substring(0, idx)
            //Timber.e("marketKey : $marketKey")

            dao.getTickers("$marketKey%")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}