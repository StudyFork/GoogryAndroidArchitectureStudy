package study.architecture.myarchitecture.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import study.architecture.myarchitecture.data.model.UpbitMarket
import study.architecture.myarchitecture.data.model.UpbitTicker
import study.architecture.myarchitecture.data.source.local.UpbitLocalDataSource
import study.architecture.myarchitecture.data.source.remote.UpbitRemoteDataSource
import study.architecture.myarchitecture.util.TextUtil
import java.util.regex.Pattern

class UpbitRepositoryImpl(
    private val upbitRemoteDataSource: UpbitRemoteDataSource,
    private val upbitLocalDataSource: UpbitLocalDataSource,
    private val isOnline: Boolean
) : UpbitRepository {

    override fun getGroupedMarkets(): Single<Map<String, List<UpbitMarket>>> {
        //Timber.d("isOnline : $isOnline")
        return if (isOnline) {

            upbitRemoteDataSource.getMarkets()
                .flatMap { markets ->

                    if (isOnline) {
                        upbitLocalDataSource.clearMarkets()
                        for (market in markets) {
                            //Timber.d("upbitRemoteDataSource market 저장 -> $market")
                            upbitLocalDataSource.insertMarket(market)
                        }
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

            upbitLocalDataSource.getMarkets()
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
            upbitRemoteDataSource.getTickers(markets)
                .flatMap { tickers ->

                    val idx = markets.indexOf("-")
                    val marketKey = markets.substring(0, idx)
                    //Timber.e("clear marketKey : $marketKey")

                    upbitLocalDataSource.clearTickers("$marketKey%")

                    for (ticker in tickers) {
                        //Timber.d("upbitRemoteDataSource ticker 저장 -> $ticker")
                        ticker.setCoinName(TextUtil.getCoinName(ticker.market))
                        ticker.setLast(TextUtil.getLast(ticker.tradePrice))
                        ticker.setTradeDiff(TextUtil.getTradeDiff(ticker.signedChangeRate))
                        ticker.setTradeAmount(TextUtil.getTradeAmount(ticker.accTradePrice24h))

                        upbitLocalDataSource.insertTicker(ticker)
                    }

                    Single.just(tickers)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        } else {
            val idx = markets.indexOf("-")
            val marketKey = markets.substring(0, idx)
            //Timber.e("marketKey : $marketKey")

            upbitLocalDataSource.getTickers("$marketKey%")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}