package com.song2.myapplication.ui

import com.song2.myapplication.source.MovieRepositoryImpl

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private var movieRepository = MovieRepositoryImpl()

    override fun getMovie(keyword: String) {

        movieRepository.getMovieData(keyword, 20,
            onSuccess = {
                view.showGetMovieSuccess(it)
            },
            onFailure = {
                view.showGetMovieFailure(it)
            }
        )
    }

    // TODO : 무한스크롤 - 이전 데이터에 이어서 추가로 데이터를 받아오는 방법에 대한 기능 구현 or getMovie 메소드와 합치기...
    override fun getMoreMovie(keyword: String) {

        movieRepository.getMovieData(keyword, 20,
            onSuccess = {
                view.showGetMoreDataSuccess(it)
            },
            onFailure = {}
        )
    }
}