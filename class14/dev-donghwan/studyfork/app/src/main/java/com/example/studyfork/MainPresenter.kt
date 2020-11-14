package com.example.studyfork

import com.example.studyfork.data.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class MainPresenter(
    private val view: MainContract.View,
    private val repository: Repository
) : MainContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    override fun requestClearDisposable() {
        this.compositeDisposable.clear()
    }

    override fun requestMovieList(query: String) {
        if (query.isEmpty()) {
            view.showQueryEmpty()
            return
        }

        this.repository.searchMovie(
            query = query,
            success = {
                it.items.run {
                    view.showMovieList(this)
                }
            },
            fail = {
                view.showMovieError()
                it.printStackTrace()
            }).addTo(compositeDisposable)
    }
}