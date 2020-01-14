package com.ironelder.androidarchitecture.view.mainview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.ironelder.androidarchitecture.data.ResultItem
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.dao.SearchResultDao
import com.ironelder.androidarchitecture.data.repository.SearchDataRepositoryImpl
import com.ironelder.androidarchitecture.view.baseview.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val searchDataRepositoryImpl: SearchDataRepositoryImpl) : BaseViewModel() {
    private val LOG_TAG = MainViewModel::class.java.toString()

    val showProgressBar = MutableLiveData<Boolean>(false)
    val notifyErrorMessage = MutableLiveData<String>()
    val searchQuery = MutableLiveData<String>()
    val searchResultList = MutableLiveData<List<ResultItem>>()

    fun searchWithAdapter(
        type: String, searchResultDao: SearchResultDao
    ) {
        searchDataRepositoryImpl.getRemoteSearchData(
            type,
            searchQuery.value.toString(),
            searchResultDao
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showProgressBar.value = true
            }
            .doAfterTerminate {
                showProgressBar.value = false
            }
            .subscribe(::onSuccess, ::onError)
            .addDisposable()
    }

    private fun onSuccess(result: TotalModel) {
        searchResultList.value = result.items
    }

    private fun onError(t: Throwable) {
        notifyErrorMessage.value = t.message
    }

    fun getSearchResultToRoom(
        type: String,
        searchResultDao: SearchResultDao
    ) {
        searchDataRepositoryImpl.getLocalSearchData(type, searchResultDao)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
                val test = Gson().fromJson(
                    it.result,
                    Array<ResultItem>::class.java
                )
                searchResultList.value = test.toList()
                searchQuery.value = it.search
            }, { t: Throwable? -> Log.w(LOG_TAG, t?.message ?: "DataBase $type Not Save Data") })
            ?.addDisposable()
    }

}