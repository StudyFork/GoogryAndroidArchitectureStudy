package study.architecture.myarchitecture.ui.tickerlist

import android.view.View
import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.ui.model.TickerItem
import study.architecture.myarchitecture.ui.model.mapToPresentation
import timber.log.Timber

class TickerListViewModel(
    private val upbitRepository: UpbitRepository,
    private val keyMarket: String,
    private val tickerAdapter: TickerAdapter,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) {

    private var tickers: MutableList<TickerItem> = mutableListOf()

    val adapter = ObservableField<TickerAdapter>()
    val isProgress = ObservableField<Int>()

    init {
        adapter.set(tickerAdapter)
    }

    fun detachView() {
        compositeDisposable.clear()
    }

    fun loadData() {

        upbitRepository
            .getTickers(keyMarket)
            .doOnSubscribe {
                isProgress.set(View.VISIBLE)
            }
            .doOnTerminate {
                isProgress.set(View.GONE)
            }
            .subscribe({

                tickers = it.mapToPresentation().toMutableList()
                tickerAdapter.setItems(tickers)

            }) {
                Timber.e(it)
            }.also {
                compositeDisposable.add(it)
            }
    }
}