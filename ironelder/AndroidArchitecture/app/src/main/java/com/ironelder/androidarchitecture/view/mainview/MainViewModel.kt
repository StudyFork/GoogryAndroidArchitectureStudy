package com.ironelder.androidarchitecture.view.mainview

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.google.gson.Gson
import com.ironelder.androidarchitecture.data.ResultItem
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.database.SearchResultDatabase
import com.ironelder.androidarchitecture.data.repository.SearchDataRepositoryImpl
import com.ironelder.androidarchitecture.view.baseview.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : BaseViewModel() {
    private val LOG_TAG = MainViewModel::class.java.toString()

    val showProgressBar:ObservableField<Boolean> = ObservableField(false)
    val notifyErrorMessage:ObservableField<String> = ObservableField()
    val searchQuery:ObservableField<String> = ObservableField()
    val searchResultList:ObservableArrayList<ResultItem> = ObservableArrayList()


    fun searchWithAdapter(
        type: String, query: String?, searchResultDatabase: SearchResultDatabase?
    ) {
        SearchDataRepositoryImpl.getRemoteSearchData(
            type,
            query,
            searchResultDatabase
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showProgressBar.set(true)
            }
            .doAfterTerminate {
                showProgressBar.set(false)
            }
            .subscribe(::onSuccess, ::onError)
            .addDisposable()
    }

    private fun onSuccess(result: TotalModel) {
        searchResultList.addAll(result.items)
    }

    private fun onError(t: Throwable) {
        notifyErrorMessage.set(t.message)
    }

    fun getSearchResultToRoom(
        type: String,
        searchResultDatabase: SearchResultDatabase?
    ) {
        SearchDataRepositoryImpl.getLocalSearchData(type, searchResultDatabase)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
                searchResultList.clear()
                searchResultList.addAll(Gson().fromJson(
                    it.result,
                    Array<ResultItem>::class.java
                ))
                searchQuery.set(it.search)
            }, { t: Throwable? -> Log.w(LOG_TAG, t?.message ?: "DataBase $type Not Save Data") })
            ?.addDisposable()
    }

}