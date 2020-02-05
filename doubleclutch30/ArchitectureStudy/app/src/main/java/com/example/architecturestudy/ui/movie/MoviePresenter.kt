package com.example.architecturestudy.ui.movie

import com.example.architecturestudy.data.repository.NaverSearchRepository

class MoviePresenter(
    val view: MovieContract.View,
    private val repository: NaverSearchRepository?
) : MovieContract.Presenter {
    override fun taskSearch(isNetwork: Boolean, keyword: String) {
        repository?.getMovie(
            isNetwork = isNetwork,
            keyword = keyword,
            success = { view.showResult(it) },
            fail = { e -> view.showErrorMessage(e.toString()) }
        )
    }

    override fun getLastData() {
        repository?.getLastMovie(
            success = { view.showResult(it) },
            fail = { e -> view.showEmpty(e.toString()) }
        )
    }
}