package com.wybh.androidarchitecturestudy.main

import com.wybh.androidarchitecturestudy.CinemaItem
import com.wybh.androidarchitecturestudy.model.local.NaverLocalDataSourceImpl
import com.wybh.androidarchitecturestudy.model.remote.NaverRemoteDataSourceImpl
import com.wybh.androidarchitecturestudy.model.repository.RepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val composeDisposable = CompositeDisposable()
    private val list by lazy {
        ArrayList<CinemaItem>()
    }
    private val repository: RepositoryImpl by lazy {
        val remoteNaverDataSource = NaverRemoteDataSourceImpl()
        val localNaverDataSource = NaverLocalDataSourceImpl()
        RepositoryImpl(remoteNaverDataSource, localNaverDataSource)
    }

    override fun removeCompositeDisposable() {
        composeDisposable.dispose()
    }

    override fun searchCinema(query: String) {
        if (query.isEmpty()) {
            return
        }
        composeDisposable.add(
            repository.searchCinema(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map { response ->
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
                }.subscribe({
                    view.showCinemaList(list)
                }, {
                    view.showToastFailMessage(it.message)
                })
        )
    }

    override fun saveSearchWord(word: String) {
        if (word.isEmpty()) {
            return
        }
        repository.saveSearchWord(word)
    }

}