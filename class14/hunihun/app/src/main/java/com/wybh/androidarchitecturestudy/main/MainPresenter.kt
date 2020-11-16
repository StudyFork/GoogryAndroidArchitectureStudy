package com.wybh.androidarchitecturestudy.main

import com.wybh.androidarchitecturestudy.CinemaItem
import com.wybh.androidarchitecturestudy.model.remote.NaverRemoteDataSourceImpl
import com.wybh.androidarchitecturestudy.model.repository.RepositoryImpl

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun removeCompositeDisposable() {
        repository.removeCompositeDisposable()
    }

    private val repository: RepositoryImpl by lazy {
        val remoteNaverApi = NaverRemoteDataSourceImpl()
        RepositoryImpl(remoteNaverApi)
    }

    override fun searchCinema(query: String) {
        repository.searchCinema(query, {
            val list = ArrayList<CinemaItem>()
            it.items.map { response ->
                val item = CinemaItem(
                    response.image,
                    response.title,
                    response.actor,
                    response.userRating,
                    response.pubDate,
                    response.link
                )
                list.add(item)
            }
            view.showCinemaList(list)
        }, {
            view.showToastFailMessage(it.message)
        })
    }

}