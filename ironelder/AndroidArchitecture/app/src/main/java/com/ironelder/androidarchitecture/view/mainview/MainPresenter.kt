package com.ironelder.androidarchitecture.view.mainview

import android.util.Log
import androidx.databinding.ObservableArrayList
import com.google.gson.Gson
import com.ironelder.androidarchitecture.data.ResultItem
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.database.SearchResultDatabase
import com.ironelder.androidarchitecture.data.repository.SearchDataRepositoryImpl
import com.ironelder.androidarchitecture.view.baseview.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private val LOG_TAG = MainPresenter::class.java.toString()

    override fun searchWithAdapter(
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
                view.showLoading()
            }
            .doAfterTerminate {
                view.hideLoading()
            }
            .subscribe(::onSuccess, ::onError)
            .addDisposable()
    }

    private fun onSuccess(result: TotalModel) {
        view.onDataChanged(result.items)
    }

    private fun onError(t: Throwable) {
        view.showErrorMessage(t.message)
    }

    override fun getSearchResultToRoom(
        type: String,
        searchResultDatabase: SearchResultDatabase?
    ) {
        SearchDataRepositoryImpl.getLocalSearchData(type, searchResultDatabase)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
                view.onLoadFromDatabase(
                    it.search,
                    Gson().fromJson(
                        it.result,
                        Array<ResultItem>::class.java
                    ).toCollection(ObservableArrayList())
                )
            }, { t: Throwable? -> Log.w(LOG_TAG, t?.message ?: "DataBase $type Not Save Data") })
            ?.addDisposable()
    }

}