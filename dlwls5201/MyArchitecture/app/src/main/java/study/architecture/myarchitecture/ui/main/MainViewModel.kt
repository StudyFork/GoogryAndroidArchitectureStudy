package study.architecture.myarchitecture.ui.main

import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import study.architecture.myarchitecture.data.repository.UpbitRepository
import timber.log.Timber

class MainViewModel(
    private val upbitRepository: UpbitRepository,
    private val mainAdapter: MainAdapter,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) {

    val pageLimit = ObservableField<Int>()
    val adapter = ObservableField<MainAdapter>()

    init {
        adapter.set(mainAdapter)
    }

    fun detachView() {
        compositeDisposable.clear()
    }

    fun loadData() {
        upbitRepository.getGroupedMarkets()
            .subscribe({ groupMarket ->

                val keys = groupMarket.keys

                mainAdapter.setTitles(keys.toTypedArray())
                pageLimit.set(keys.size)

                val arrMarkets = Array(keys.size) { "" }

                for ((index, value) in keys.withIndex()) {

                    arrMarkets[index] = groupMarket
                        .getValue(value)
                        .joinToString(separator = ",") { it.market }
                }

                mainAdapter.setItems(arrMarkets)

            }) {
                Timber.e(it)
            }.also {
                compositeDisposable.add(it)
            }
    }
}