package com.example.studyfork

import com.example.studyfork.data.repository.Repository

class MainPresenter(
    private val view: MainContract.View,
    private val repository: Repository
) : MainContract.Presenter {
    override fun requestMovieList(query: String) {
        if (query.isEmpty()) {
            view.showQueryEmpty()
        }

        this.repository.searchMovie(
            query = query,
            success = {
                it.items.run {
                    view.showMovieList(this)
                }
            },
            fail = {
                it.printStackTrace()
            })
    }
}