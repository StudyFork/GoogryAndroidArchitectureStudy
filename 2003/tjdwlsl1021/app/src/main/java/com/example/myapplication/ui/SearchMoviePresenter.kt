package com.example.myapplication.ui


///**
// * Presenter : 로직, 계산, if문
// * */
//class SearchMoviePresenter(
//    private val view: SearchMovieContract.View,
//    private val movieRepositoryDataSet: MovieRepositoryDataSet
//) : SearchMovieContract.Presenter {
//
//    override fun searchMovie(etMovieTitle: String) {
//        if (etMovieTitle.isEmpty()) {
//            view.showToastMovieTitleIsEmpty()
//        } else {
//            movieRepositoryDataSet.movieRepository.getMovieList(
//                etMovieTitle,
//                success = { view.addItems(it) },
//                failed = { view.recordLog(it.toString()) })
//        }
//    }
//}