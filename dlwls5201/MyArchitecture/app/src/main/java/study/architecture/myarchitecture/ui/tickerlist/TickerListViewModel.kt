package study.architecture.myarchitecture.ui.tickerlist

import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import study.architecture.myarchitecture.data.repository.UpbitRepository

class TickerListViewModel(
    private val upbitRepository: UpbitRepository,
    private val keyMarket: String,
    private val tickerAdapter: TickerAdapter,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) {

    val adapter = ObservableField<TickerAdapter>()

    init {
        adapter.set(tickerAdapter)
    }

    fun detachView() {
        compositeDisposable.clear()
    }
}