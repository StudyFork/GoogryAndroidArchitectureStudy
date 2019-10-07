package study.architecture.myarchitecture.ui.tickerlist

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _tickers = MutableLiveData<MutableList<TickerItem>>()
    private val _isProgress = MutableLiveData<Int>()

    val tickers: LiveData<MutableList<TickerItem>> get() = _tickers
    val isProgress: LiveData<Int> get() = _isProgress

    init {
        loadData()
    }

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

        _tickers.value = mTickers
    }

    private fun loadData() {
        addDisposable(
            upbitRepository
                .getTickers(keyMarket)
                .doOnSubscribe {
                    _isProgress.value = View.VISIBLE
                }
                .doOnTerminate {
                    _isProgress.value = View.GONE
                }
                .subscribe({

                    _tickers.value = it.mapToPresentation().toMutableList()

                    mTickers.clear()
                    mTickers.addAll(_tickers.value!!)

                    sortByField(Filter.SelectArrow.COIN_NAME, Filter.ASC)

                }) {
                    Timber.e(it)
                }

        )
    }
}