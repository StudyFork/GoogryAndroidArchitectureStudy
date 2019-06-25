package dev.daeyeon.gaasproject.data.source

import dev.daeyeon.gaasproject.data.Ticker
import dev.daeyeon.gaasproject.data.response.ResponseCode
import dev.daeyeon.gaasproject.network.UpbitApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class UpbitRepository(private val upbitApi: UpbitApi) : UpbitDataSource {

    override var markets: String = ""

    private val compositeDisposable = CompositeDisposable()

    override fun getTicker(
        baseCurrency: String,
        searchTicker: String,
        success: (tickerList: List<Ticker>) -> Unit,
        fail: (msg: String) -> Unit
    ) {
        getMarkets(
            success = { result ->
                markets = result

                if (compositeDisposable.size() >= 1) {
                    unsubscribeTicker()
                }

                Observable.interval(0, 5000, TimeUnit.MILLISECONDS)
                    .flatMap { upbitApi.getTicker(markets) }
                    .subscribeOn(Schedulers.io())
                    .map {
                        it.filter {
                            it.market.contains(
                                "$baseCurrency-" +
                                        if (searchTicker == UpbitDataSource.ALL_CURRENCY) {
                                            ""
                                        } else {
                                            searchTicker.toUpperCase()
                                        }
                            )
                        }
                            .map { response -> response.toTicker() }
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { tickerList ->
                            if (tickerList.isEmpty()) {
                                fail.invoke(ResponseCode.CODE_EMPTY_SUCCESS)
                            } else {
                                success.invoke(tickerList)
                            }
                        },
                        { t ->
                            fail.invoke(t.message ?: ResponseCode.CODE_NULL_FAIL_MSG)
                        }
                    )
                    .addTo(compositeDisposable)
            },
            fail = {
                fail.invoke(it)
            }
        )
    }

    override fun getMarkets(success: (markets: String) -> Unit, fail: (msg: String) -> Unit) {
        if (markets.isEmpty()) {
            upbitApi.getMarketCode().let {
                it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { marketList ->
                            if (marketList.isEmpty()) {
                                fail.invoke(ResponseCode.CODE_EMPTY_SUCCESS)
                            } else {
                                success.invoke(marketList.joinToString(separator = ",") { it.market })
                            }
                        },
                        { t ->
                            fail.invoke(t.message ?: ResponseCode.CODE_NULL_FAIL_MSG)
                        }
                    )
            }
        } else {
            success.invoke(markets)
        }
    }

    override fun unsubscribeTicker() {
        compositeDisposable.clear()
    }
}