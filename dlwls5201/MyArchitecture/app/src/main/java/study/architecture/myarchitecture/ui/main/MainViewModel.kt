package study.architecture.myarchitecture.ui.main

import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.util.Filter
import timber.log.Timber

class MainViewModel(
    private val upbitRepository: UpbitRepository,
    val listener: SortListener?,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) {

    val titles = ObservableField<Array<String>>()
    val markets = ObservableField<Array<String>>()

    val pageLimit = ObservableField<Int>()

    val category = ObservableField<Filter.SelectArrow>()

    fun clearDisposable() {
        compositeDisposable.clear()
    }

    fun loadData() {
        upbitRepository.getGroupedMarkets()
            .subscribe({ groupMarket ->

                val keys = groupMarket.keys

                titles.set(keys.toTypedArray())
                pageLimit.set(keys.size)

                val arrMarkets = Array(keys.size) { "" }

                for ((index, value) in keys.withIndex()) {

                    arrMarkets[index] = groupMarket
                        .getValue(value)
                        .joinToString(separator = ",") { it.market }
                }

                markets.set(arrMarkets)

            }) {
                Timber.e(it)
            }.also {
                compositeDisposable.add(it)
            }
    }

    fun showCategoryArrow(selectArrow: Filter.SelectArrow) {

        this.category.set(selectArrow)

        /**
         * 같은 값을 넣어주면 notify 되지 않는다.
         * 임의로 notifyChange 호출
         */
        this.category.notifyChange()
    }

    interface SortListener {

        fun sortTicker(field: Filter.SelectArrow, order: Int)
    }
}