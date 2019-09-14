package study.architecture.myarchitecture.ui.tickerlist

import io.reactivex.disposables.CompositeDisposable
import study.architecture.myarchitecture.data.repository.UpbitRepository

class TickerListViewModel(
    private val upbitRepository: UpbitRepository,
    private val keyMarket: String,
    private val tickerAdapter: TickerAdapter,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) {

    fun detachView() {
        compositeDisposable.clear()
    }
}