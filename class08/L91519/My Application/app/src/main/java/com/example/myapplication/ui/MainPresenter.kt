package com.example.myapplication.ui

import com.example.myapplication.data.repository.NaverRepositoryImpl

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun findMovie(query: String) {
        query?:view.queryNone()
        NaverRepositoryImpl.getResultData(query,
            success = {
                if (it.items.isEmpty()) {
                    view.resultNone()
                } else {
                    view.updateMovieRecycler(it.items)
                    saveCache()
                }
            },
            fail = { view.failMovieGet(it.message.toString()) })


    }

    override fun resentData() {
        NaverRepositoryImpl.getResentData(
            success = {

            },
            fail = {

            }
        )
    }

    override fun saveCache() {

    }


}