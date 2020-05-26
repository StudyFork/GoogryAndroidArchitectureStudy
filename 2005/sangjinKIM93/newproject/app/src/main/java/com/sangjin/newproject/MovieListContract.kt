package com.sangjin.newproject

import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.model.NaverMovieResponse
import io.reactivex.Single

interface MovieListContract{
    interface View {
        fun noKeyword()
        fun showMovieList(movies: List<Movie>)
        fun noResult(movies: List<Movie>)
        fun onError(t: Throwable)
    }

    interface Presenter {
        fun loadCache()
        fun searchMovie(keyword: String)
        fun checkMovieResult(movies: List<Movie>)
        fun clearDisposable()
    }
}