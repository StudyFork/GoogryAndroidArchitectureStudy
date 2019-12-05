package com.android.studyfork.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.studyfork.base.BaseViewModel
import com.android.studyfork.repository.UpbitRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/**
 * created by onemask
 */
class MainViewModel @Inject constructor(private val repository: UpbitRepositoryImpl) :
    BaseViewModel() {

    private val _datas = MutableLiveData<Pair<List<String>, List<String>>>()
    val datas: LiveData<Pair<List<String>, List<String>>>
        get() = _datas

    init {
        loadData()
    }

    private fun loadData() {
        repository.getMarketAll()
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
                _datas.postValue(it)
            }, { it.printStackTrace() })
            .addTo(disposable)
    }

}