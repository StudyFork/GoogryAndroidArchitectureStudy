package com.tsdev.tsandroid.presenter

import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.base.BasePresenter
import com.tsdev.tsandroid.data.repository.NaverReopsitory
import com.tsdev.tsandroid.eventbus.RxEventBus
import com.tsdev.tsandroid.provider.ResourceProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviePresenter(
    override val view: MovieContract.View,
    private val movieRepositoryImpl: NaverReopsitory,
    private val resourceProvider: ResourceProvider,
    override val rxEventBus: RxEventBus
) : MovieContract.Presenter, BasePresenter<MovieContract.View>() {

    override var isLoading: Boolean = false

    override fun loadMovie(query: String) {
        isLoading = true
        compositeDisposable.add(
            movieRepositoryImpl.getMovieList(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    view.onHideSoftKeyboard()
                }
                .subscribe({
                    isLoading = if (it.isNotEmpty()) {
                        view.showSearchResult(it)
                        false
                    } else {
                        view.showToastMessage(resourceProvider.getResultErrorString(R.string.non_search_result))
                        view.removeAll()
                        false
                    }
                }, {
                    it.printStackTrace()
                    view.showToastMessage(resourceProvider.getResultErrorString(R.string.occur_error_toast))
                })
        )
    }

    override fun onPressBackButton() {
        rxEventBus.sendBackButtonEvent(System.currentTimeMillis())
    }
}