package com.jay.architecturestudy.ui.movie

import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BasePresenter

class MoviePresenter(
    override val view: MovieContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BasePresenter(view, repository), MovieContract.Presenter {

    override fun search(keyword: String) {
        repository.getMovie(
            keyword = keyword,
            success = { responseMovie ->
                view.updateResult(responseMovie.movies)
            },
            fail = { e ->
                handlerError(e)
            }
        )
    }
}