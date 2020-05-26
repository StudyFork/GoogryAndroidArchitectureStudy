package com.lllccww.studyforkproject.ui.main

import com.lllccww.studyforkproject.data.repository.NaverMovieRepositoryImpl

class MainPresenter(
    private val naverMovieRepository: NaverMovieRepositoryImpl,
    private val view: MainContract.View
) : MainContract.Presenter {

    override fun getSearchMovie(query: String) {

        if (query.isNullOrEmpty()) {
            view.showMovieEmptySearchQuery()
            return

        }

        naverMovieRepository.getSearchMovie(
            query,
            success = { movieItem ->
                if (movieItem.isNullOrEmpty()) {
                    view.showMovieNoResult()
                } else {
                    view.showMovieList(movieItem)
                }
            },
            failure = {
                view.showFailGetData(it.message.toString())
            }
        )

    }
}