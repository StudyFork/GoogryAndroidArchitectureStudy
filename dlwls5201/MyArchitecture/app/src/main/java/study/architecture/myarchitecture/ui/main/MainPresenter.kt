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

                view.setViewPagerTitles(keys.toTypedArray())

                val arrMarkets = Array(keys.size) { "" }

                for ((index, value) in keys.withIndex()) {

                    arrMarkets[index] = groupMarket
                        .getValue(value)
                        .joinToString(separator = ",") { it.market }
                }

                view.setViewPagers(arrMarkets)
            }) {
                Timber.e(it)
            }.also {
                compositeDisposable.add(it)
            }
    }

    override fun changeArrow(selectArrow: MainActivity.SelectArrow) {

        view.setCoinNameVisibility(View.INVISIBLE)
        view.setLastVisibility(View.INVISIBLE)
        view.setTradeDiffVisibility(View.INVISIBLE)
        view.setTradeAmountVisibility(View.INVISIBLE)

        when (selectArrow) {

            MainActivity.SelectArrow.COIN_NAME -> {
                view.setCoinNameVisibility(View.VISIBLE)

                if (view.getCoinNameIsSelected()) {
                    view.notifyTickerListObervers(Filter.COIN_NAME, Filter.DESC)
                } else {
                    view.notifyTickerListObervers(Filter.COIN_NAME, Filter.ASC)
                }

                view.setCoinNameIsSelected(!view.getCoinNameIsSelected())
            }

            MainActivity.SelectArrow.LAST -> {
                view.setLastVisibility(View.VISIBLE)

                if (view.getLastIsSelected()) {
                    view.notifyTickerListObervers(Filter.LAST, Filter.DESC)
                } else {
                    view.notifyTickerListObervers(Filter.LAST, Filter.ASC)
                }

                view.setLastIsSelected(!view.getLastIsSelected())
            }

            MainActivity.SelectArrow.TRADE_DIFF -> {
                view.setTradeDiffVisibility(View.VISIBLE)

                if (view.getTradeDiffIsSelected()) {
                    view.notifyTickerListObervers(Filter.TRADE_DIFF, Filter.DESC)
                } else {
                    view.notifyTickerListObervers(Filter.TRADE_DIFF, Filter.ASC)
                }

                view.setTradeDiffIsSelected(!view.getTradeDiffIsSelected())
            }

            MainActivity.SelectArrow.TRADE_AMOUNT -> {
                view.setTradeAmountVisibility(View.VISIBLE)

                if (view.getTradeAmountIsSelected()) {
                    view.notifyTickerListObervers(Filter.TRADE_AMOUNT, Filter.DESC)
                } else {
                    view.notifyTickerListObervers(Filter.TRADE_AMOUNT, Filter.ASC)
                }

                view.setTradeAmountIsSelected(!view.getTradeAmountIsSelected())
            }
        }
    }

}