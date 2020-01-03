package com.example.androidstudy.ui.base

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.ObservableField
import com.example.androidstudy.database.SearchResultDatabase
import com.example.androidstudy.model.data.Item
import com.example.androidstudy.model.data.SearchResultEntity
import com.example.androidstudy.model.data.TotalModel
import com.example.androidstudy.model.repository.NaverDataRepositoryImpl
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BaseViewModel {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    lateinit var type: String
    lateinit var database: SearchResultDatabase

    var query: ObservableField<String> = ObservableField()
    var searchResult: ObservableField<List<Item>> = ObservableField()

    var onLoading: ObservableField<Boolean> = ObservableField()
    var noDataError: ObservableField<String> = ObservableField()

    private fun search(query: String?, type: String) {
        NaverDataRepositoryImpl.getNaverSearchData(
            type,
            query!!
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                onLoading.set(true)
            }
            .doAfterTerminate {
                onLoading.set(false)
            }
            .doAfterSuccess {
                insertSeachResult(
                    database,
                    SearchResultEntity(null, type, query, Gson().toJson(it?.items))
                )

                searchResult.set(it?.items)
            }
            .subscribe(::onSuccess, ::onError)
            .addDisposable()
    }

    private fun onSuccess(result: TotalModel) {
        if (result.items.isNullOrEmpty()) {
            noDataError.set("데이터가 없습니다.")
        } else {
            noDataError.set("")
        }
    }

    private fun onError(t: Throwable) {
        println(t.message)
        noDataError.set(t.message)
    }

    private fun insertSeachResult(
        searchResultDatabase: SearchResultDatabase?,
        searchResult: SearchResultEntity
    ) {
        NaverDataRepositoryImpl.setLocalSearchData(searchResultDatabase, searchResult)
    }

    fun onEditorActionClicked(view: TextView, actionId: Int?, event: KeyEvent?): Boolean {
        when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> {
                search(view.text.toString(), this.type)
            }
        }
        return false
    }

    private fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

}