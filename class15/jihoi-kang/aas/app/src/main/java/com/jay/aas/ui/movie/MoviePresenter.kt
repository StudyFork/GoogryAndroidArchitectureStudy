package com.jay.aas.ui.movie

import com.jay.aas.data.MovieRepository

//class MoviePresenter(
//    private val view: MovieContract.View,
//    private val movieRepository: MovieRepository,
//) : BasePresenter(), MovieContract.Presenter {
//
//    override suspend fun getMovies() {
//        val movies = movieRepository.getMovies()
//
//        if (movies.isEmpty()) {
//            view.showNoResult()
//        } else {
//            view.showMovieItems(movies)
//        }
//    }
//
//    override suspend fun searchMovies(query: String) {
//        if (query.isEmpty()) return
//
//        view.hideKeyboard()
//        try {
//            view.showProgressBar()
//            val movies = movieRepository.getSearchMovies(query)
//            view.hideProgressBar()
//
//            if (movies.isEmpty()) {
//                view.showNoResult()
//            } else {
//                view.showMovieItems(movies)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            view.hideProgressBar()
//            view.showSearchFailed()
//        }
//    }
//
//    override fun openMovieDetail(link: String) {
//        view.openMovieDetail(link)
//    }
//
//}