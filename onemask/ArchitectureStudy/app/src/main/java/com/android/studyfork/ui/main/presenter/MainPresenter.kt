package com.android.studyfork.ui.main.presenter

import com.android.studyfork.repository.UpbitRepositoryImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber

/**
 * created by onemask
 */
class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    private val disposables = CompositeDisposable()

    override fun loadData() {
        disposables += UpbitRepositoryImpl.getMarketAll()
            .subscribe({ marketMap ->
               view.setViewPagerData(marketMap)
            },
            {
                Timber.e(it)
            }
        )
    }

    override fun clearDisposable() {
        disposables.clear()
    }

}



