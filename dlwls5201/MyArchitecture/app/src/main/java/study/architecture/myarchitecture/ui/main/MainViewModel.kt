package study.architecture.myarchitecture.ui.main

import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.util.Filter
import timber.log.Timber

class MainViewModel(
    private val upbitRepository: UpbitRepository,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) {

    val viewPagers = ObservableField<Array<String>>()

    val tabTitles = ObservableField<Array<String>>()

    val tabArraow = ObservableField<Filter.SelectArrow>()

    fun detachView() {
        compositeDisposable.clear()
    }

    fun loadData() {
        upbitRepository.getGroupedMarkets()
            .subscribe({ groupMarket ->

                val keys = groupMarket.keys

                //view.showViewPagerTitles(keys.toTypedArray())

                val arrMarkets = Array(keys.size) { "" }

                for ((index, value) in keys.withIndex()) {

                    arrMarkets[index] = groupMarket
                        .getValue(value)
                        .joinToString(separator = ",") { it.market }
                }

                //view.showViewPagers(arrMarkets)

            }) {
                Timber.e(it)
            }.also {
                compositeDisposable.add(it)
            }
    }
}