package study.architecture.myarchitecture.ui.tickerlist

import android.view.View
import androidx.databinding.ObservableField
import study.architecture.myarchitecture.base.BaseViewModel
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.ui.model.TickerItem
import study.architecture.myarchitecture.ui.model.mapToPresentation
import study.architecture.myarchitecture.util.Filter
import timber.log.Timber

class TickerListViewModel(
    private val upbitRepository: UpbitRepository,
    private val keyMarket: String
) : BaseViewModel() {

    private val mTickers = mutableListOf<TickerItem>()

    val tickers = ObservableField<MutableList<TickerItem>>()
    val isProgress = ObservableField<Int>()

    fun sortByField(field: Filter.SelectArrow, order: Int) {

        when (field) {
            Filter.SelectArrow.COIN_NAME -> {
                val selector: (TickerItem) -> String = { it.coinName }
                setOrderByField(selector, order)
            }

            Filter.SelectArrow.LAST -> {
                val selector: (TickerItem) -> Double = { it.tradePrice }
                setOrderByField(selector, order)
            }

            Filter.SelectArrow.TRADE_DIFF -> {
                val selector: (TickerItem) -> Double = { it.signedChangeRate }
                setOrderByField(selector, order)
            }

            Filter.SelectArrow.TRADE_AMOUNT -> {
                val selector: (TickerItem) -> Double = { it.accTradePrice24h }
                setOrderByField(selector, order)
            }
        }
    }

    private fun <R : Comparable<R>> setOrderByField(
        selector: (TickerItem) -> R, order: Int
    ) {

        if (order == Filter.ASC) {
            mTickers.sortBy(selector)
        } else if (order == Filter.DESC) {
            mTickers.sortByDescending(selector)
        }

        /**
         * ObservableField의 set()함수는 다른 객체를 넣어줘야 동작이 됩니다.
         * mTickers를 sorting해도 같은 객체이므로 아래 코드는 1회만 동작되게 됩니다.
         * 이에 강제로 notifyChange를 호출해 줍니다.
         */
        tickers.set(mTickers)
        tickers.notifyChange()
    }

    fun loadData() {
        addDisposable(
            upbitRepository
                .getTickers(keyMarket)
                .doOnSubscribe {
                    isProgress.set(View.VISIBLE)
                }
                .doOnTerminate {
                    isProgress.set(View.GONE)
                }
                .subscribe({

                    tickers.set(it.mapToPresentation().toMutableList())

                    mTickers.clear()
                    mTickers.addAll(tickers.get()!!)

                }) {
                    Timber.e(it)
                }

        )
    }
}