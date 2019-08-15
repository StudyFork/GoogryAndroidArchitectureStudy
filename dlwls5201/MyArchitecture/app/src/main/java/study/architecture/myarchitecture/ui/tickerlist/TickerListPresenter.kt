package study.architecture.myarchitecture.ui.tickerlist

import io.reactivex.disposables.CompositeDisposable
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.ui.model.TickerItem
import study.architecture.myarchitecture.ui.model.mapToPresentation
import study.architecture.myarchitecture.util.Filter
import timber.log.Timber

class TickerListPresenter(
    private val upbitRepository: UpbitRepository,
    private val view: TickerListContract.View,
    private val keyMarket: String,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : TickerListContract.Presenter {

    override fun detachView() {
        compositeDisposable.clear()
    }

    override fun sortByField(tickers: MutableList<TickerItem>, field: String, order: Int) {

        when (field) {

            Filter.COIN_NAME -> {
                val selector: (TickerItem) -> String = { it.coinName }
                setOrderByField(tickers, selector, order)
            }

            Filter.LAST -> {
                val selector: (TickerItem) -> Double = { it.tradePrice }
                setOrderByField(tickers, selector, order)
            }

            Filter.TRADE_DIFF -> {
                val selector: (TickerItem) -> Double = { it.signedChangeRate }
                setOrderByField(tickers, selector, order)
            }

            Filter.TRADE_AMOUNT -> {
                val selector: (TickerItem) -> Double = { it.accTradePrice24h }
                setOrderByField(tickers, selector, order)
            }
        }
    }

    private fun <R : Comparable<R>> setOrderByField(
        tickers: MutableList<TickerItem>, selector: (TickerItem) -> R, order: Int
    ) {

        Timber.d("tickers : $tickers")

        if (order == Filter.ASC) {
            tickers.sortBy(selector)
        } else if (order == Filter.DESC) {
            tickers.sortByDescending(selector)
        }

        Timber.e("tickers : $tickers")
        view.showTickers(tickers)
    }

    override fun loadData() {

        upbitRepository
            .getTickers(keyMarket)
            .doOnSubscribe {
                view.showProgress()
            }
            .doOnTerminate {
                view.hideProgress()
            }
            .subscribe({

                view.showTickers(it.mapToPresentation().toMutableList())

            }) {
                Timber.e(it)
            }.also {
                compositeDisposable.add(it)
            }
    }
}