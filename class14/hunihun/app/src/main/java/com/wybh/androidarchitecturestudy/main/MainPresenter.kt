package com.wybh.androidarchitecturestudy.main

import com.wybh.androidarchitecturestudy.CinemaItem
import com.wybh.androidarchitecturestudy.data.ResponseCinemaData
import com.wybh.androidarchitecturestudy.model.remote.NaverRemoteDataSourceImpl
import com.wybh.androidarchitecturestudy.model.repository.RepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val composeDisposable = CompositeDisposable()
    private val repository: RepositoryImpl by lazy {
        val remoteNaverApi = NaverRemoteDataSourceImpl()
        RepositoryImpl(remoteNaverApi)
    }

    override fun removeCompositeDisposable() {
        composeDisposable.dispose()
    }

    override fun searchCinema(query: String) {
        composeDisposable.add(
            repository.searchCinema(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ response: ResponseCinemaData ->
                    val list = ArrayList<CinemaItem>()
                    response.items.map {
                        val item = CinemaItem(
                            it.image,
                            it.title,
                            it.actor,
                            it.userRating,
                            it.pubDate,
                            it.link
                        )
                        list.add(item)
                    }
                    view.showCinemaList(list)
                }, { error: Throwable ->
                    view.showToastFailMessage(error.message)
                })
        )
    }

}