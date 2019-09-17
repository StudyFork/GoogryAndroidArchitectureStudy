package com.android.studyfork.ui.main.presenter

import android.annotation.SuppressLint
import com.android.studyfork.repository.UpbitRepositoryImpl
import timber.log.Timber

/**
 * created by onemask
 */
class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    @SuppressLint("CheckResult")
    override fun loadData() {
        UpbitRepositoryImpl.getMarketAll()
            .subscribe({ marketMap ->

               val titles = marketMap.keys.toTypedArray()
               val marketNames = Array(titles.count()) { "" }

               for ((index: Int, value: String) in titles.withIndex()) {
                   marketNames[index] = marketMap
                       .getValue(value)
                       .joinToString { marketResponse -> marketResponse.market }
               }
               view.setViewPagerData(titles.toList(), marketNames.toList())
            },
            {
                Timber.e(it)
            })
    }

}