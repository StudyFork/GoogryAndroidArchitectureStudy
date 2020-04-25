package com.tsdev.tsandroid.presenter

import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.data.repository.NaverReopsitory
import com.tsdev.tsandroid.provider.ResourceProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviePresenter(
    private val view: MovieContract.View,
    private val disposable: CompositeDisposable,
    private val movieRepositoryImpl: NaverReopsitory,
    private val resourceProvider: ResourceProvider
) : MovieContract.Presenter {
    override fun loadMovie(query: String) {
        disposable.add(
            movieRepositoryImpl.getMovieList(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    view.showProgressBar()
                    view.onHideSoftKeyboard()
                }
                .doAfterTerminate { view.hideProgressBar() }
                .subscribe({
                    if (it.isNotEmpty()) {
                        view.showSearchResult(it)
                    } else {
                        view.showToastMessage(resourceProvider.getResultErrorString(R.string.non_search_result))
                    }
                }, {
                    it.printStackTrace()
                    view.showToastMessage(resourceProvider.getResultErrorString(R.string.occur_error_toast))
                })
        )
    }
}