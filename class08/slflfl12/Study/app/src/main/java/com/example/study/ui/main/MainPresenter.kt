package com.example.study.ui.main

import android.util.Log
import com.example.study.data.model.Movie
import com.example.study.data.repository.NaverSearchRepository
import com.example.study.data.source.local.model.SearchResult
import com.example.study.util.extension.plusAssign
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(
    private val view: MainContract.View,
    private val naverSearchRepository: NaverSearchRepository
) : MainContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getMovies(query: String) {
        compositeDisposable += (naverSearchRepository.getMovies(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showProgress()
            }
            .doAfterTerminate {
                view.hideProgress()
            }
            .subscribe({
                it?.let {
                    if (it.items.isNotEmpty()) {
                        view.updateMovieList(it.items)
                        view.hideKeyboard()
                        naverSearchRepository.addSearchResult(SearchResult(Gson().toJson(it.items)))
                    } else {
                        view.showErrorEmptyResult()
                    }
                }
            }, {
                it.printStackTrace()
            })
        )
    }

    override fun getRecentSearchResult() {
        naverSearchRepository.getRecentSearchResult()?.let {
            if (it != null) {
                view.updateMovieList(
                    Gson().fromJson(
                        it.resultItems,
                        Array<Movie>::class.java
                    ).asList()
                )
            }
        }
    }


    override fun clearDisposable() {
        compositeDisposable.clear()
    }
}