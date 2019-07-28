package study.architecture.myarchitecture.ui.main

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.rxeventbus.RxEventBusHelper
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

    override fun sendEventBus(bundle: Bundle) {
        RxEventBusHelper.sendEvent(bundle)
    }


}