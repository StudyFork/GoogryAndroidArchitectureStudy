package com.android.studyfork.ui.main.presenter

import com.android.studyfork.base.BasePresenter
import com.android.studyfork.repository.UpbitRepositoryImpl
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

/**
 * created by onemask
 */
class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter, BasePresenter() {

    override fun loadData() {
        UpbitRepositoryImpl.getMarketAll()
            .map {
                val list = it.toList()
                list.map { it.first } to
                        list.map { it.second }.map { it.joinToString { it.market } }
            }
            .subscribe(view::setViewPagerData, Timber::e)
            .addTo(compositeDisposable)

    }

}



