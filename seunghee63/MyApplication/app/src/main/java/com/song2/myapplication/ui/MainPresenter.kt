package com.song2.myapplication.ui

import com.song2.myapplication.source.MovieRepositoryImpl

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private var movieRepository = MovieRepositoryImpl()

    override fun getMovie(keyword: String) {

        var init = view.getListCnt()

        if (init == 0){
            init = 1
        }

        movieRepository.getMovieData(keyword, 20, init,
            onSuccess = {

                if (it.count() == 0){
                    view.setResultGone()
                }else{
                    view.setResultVisible()
                }

                view.showGetMovieSuccess(it)
            },
            onFailure = {
                view.showGetMovieFailure(it)
            }
        )
    }
}