package com.song2.myapplication.ui

import com.song2.myapplication.source.MovieRepositoryImpl

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private var movieRepository = MovieRepositoryImpl()

    override fun getMovie(keyword: String) {

        movieRepository.getMovieData(keyword, 20,
            onSuccess = {
                // TODO : 무한스크롤 예외처리
                if (it.count() == 0) {
                    view.showMovieNotExist()
                } else {
                    view.shoeGetMovieSuccess()
                }
            },
            onFailure = {}
        )
    }
}