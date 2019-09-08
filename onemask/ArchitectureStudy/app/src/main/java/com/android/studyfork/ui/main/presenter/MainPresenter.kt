package com.android.studyfork.ui.main.presenter

import android.annotation.SuppressLint
import com.android.studyfork.repository.UpbitRepositoryImpl
import timber.log.Timber

/**
 * created by onemask
 */
class MainPresenter(
    private val upbitRepository: UpbitRepositoryImpl,
    private val view: MainContract.View
) : MainContract.Presenter {

    @SuppressLint("CheckResult")
    override fun loadData() {
        upbitRepository.getMarketAll()
            .subscribe({

                val keys = it.keys.apply {
                    view.setViewPagerTitle(this.toTypedArray())
                }
                val marketNamesArr = Array(keys.size) { "" }

                for ((index: Int, value: String) in keys.withIndex()) {
                    marketNamesArr[index] = it
                        .getValue(value)
                        .joinToString(",") { it.market }
                }
                view.setViewPagerData(marketNamesArr)
            }, {
                Timber.e(it)
            })
    }

}