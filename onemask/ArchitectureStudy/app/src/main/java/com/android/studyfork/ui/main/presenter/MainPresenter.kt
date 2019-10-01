package com.android.studyfork.ui.main.presenter

import com.android.studyfork.repository.UpbitRepositoryImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

/**
 * created by onemask
 */
class MainPresenter(
    override val view: MainContract.View
) : MainContract.Presenter {

    override val disposables = CompositeDisposable()

    override fun loadData() {
        UpbitRepositoryImpl.getMarketAll()
            .map {
                val list = it.toList()
                list.map { it.first } to
                        list.map { it.second }.map { it.joinToString { it.market } }
            }
            .subscribe(view::setViewPagerData, Timber::e)
            .addTo(disposables)
    }

    override fun clearDisposable() {
        disposables.clear()
    }

}



