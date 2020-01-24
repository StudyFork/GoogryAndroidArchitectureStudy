package com.song2.myapplication.ui

import android.util.Log
import com.song2.myapplication.source.MovieRepositoryImpl

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private var movieRepository = MovieRepositoryImpl()

    override fun getMovie(keyword: String) {

        fun refresh(){
            paging = 1
        }

        if (preKeyword != keyword){
            refresh()
        }

        movieRepository.getMovieData(keyword, GETMOVIECNT, paging++ * GETMOVIECNT,
            onSuccess = {

                if (it.count() == 0){
                    view.setResultGone()
                }else{
                    view.setResultVisible()
                }

                preKeyword = keyword

                view.showGetMovieSuccess(it)
            },
            onFailure = {
                view.showGetMovieFailure(it)
            }
        )
    }

    companion object{
        var preKeyword = ""
        var paging = 1
        val GETMOVIECNT = 20
    }
}

