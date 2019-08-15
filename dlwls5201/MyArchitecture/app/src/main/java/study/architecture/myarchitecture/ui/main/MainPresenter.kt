package study.architecture.myarchitecture.ui.main

import android.view.View
import io.reactivex.disposables.CompositeDisposable
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.util.Filter
import timber.log.Timber

class MainPresenter(
    private val upbitRepository: UpbitRepository,
    private val view: MainContract.View,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : MainContract.Presenter {

    override fun detachView() {
        compositeDisposable.clear()
    }

    override fun loadData() {

        upbitRepository.getGroupedMarkets()
            .subscribe({ groupMarket ->

                val keys = groupMarket.keys

                view.showViewPagerTitles(keys.toTypedArray())

                val arrMarkets = Array(keys.size) { "" }

                for ((index, value) in keys.withIndex()) {

                    arrMarkets[index] = groupMarket
                        .getValue(value)
                        .joinToString(separator = ",") { it.market }
                }

                view.showViewPagers(arrMarkets)

            }) {
                Timber.e(it)
            }.also {
                compositeDisposable.add(it)
            }
    }

    override fun changeArrow(selectArrow: MainActivity.SelectArrow) {

        view.setArrowVisibility(MainActivity.SelectArrow.COIN_NAME, View.INVISIBLE)
        view.setArrowVisibility(MainActivity.SelectArrow.LAST, View.INVISIBLE)
        view.setArrowVisibility(MainActivity.SelectArrow.TRADE_AMOUNT, View.INVISIBLE)
        view.setArrowVisibility(MainActivity.SelectArrow.TRADE_DIFF, View.INVISIBLE)

        view.setArrowVisibility(selectArrow, View.VISIBLE)

        if (view.getArrowIsSelected(selectArrow)) {
            view.showTickerListOrderByField(Filter.selectArrowToFilter(selectArrow), Filter.DESC)
        } else {
            view.showTickerListOrderByField(Filter.selectArrowToFilter(selectArrow), Filter.ASC)
        }

        view.setArrowSelected(selectArrow, !view.getArrowIsSelected(selectArrow))
    }

}