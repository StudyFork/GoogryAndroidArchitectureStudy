package com.android.studyfork.ui.main.viewmodel

import androidx.databinding.ObservableField
import com.android.studyfork.base.BaseViewModel
import com.android.studyfork.repository.UpbitRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

/**
 * created by onemask
 */
class MainViewModel : BaseViewModel() {

    val datas = ObservableField<Pair<List<String>, List<String>>>()

    init {
        loadData()
    }

    private fun loadData() {
        UpbitRepositoryImpl.getMarketAll()
            .map { response ->
                response.toList().map {
                    it.first
                } to response.toList()
                    .map { it.second }
                    .map {
                        it.joinToString {
                            it.market
                        }
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                datas.set(it)
            }, { it.printStackTrace() })
            .addTo(disposable)
    }

}
