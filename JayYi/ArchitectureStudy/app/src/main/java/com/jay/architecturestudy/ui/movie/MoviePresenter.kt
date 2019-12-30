package com.jay.architecturestudy.ui.movie

import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter

class MoviePresenter(
    override val view: MovieContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), MovieContract.Presenter {

    override fun search(keyword: String) {
        repository.getMovie(
            keyword = keyword
        )
            .subscribe({ responseMovie ->
                view.updateResult(responseMovie.movies)
            }, { e ->
                handleError(e)
            })
    }
}