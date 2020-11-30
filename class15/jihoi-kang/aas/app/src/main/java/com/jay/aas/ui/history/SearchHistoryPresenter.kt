package com.jay.aas.ui.history

import com.jay.aas.data.MovieRepository

//class SearchHistoryPresenter(
//    private val view: SearchHistoryContract.View,
//    private val movieRepository: MovieRepository,
//) : BasePresenter(), SearchHistoryContract.Presenter {
//
//    private val TAG = this::class.java.simpleName
//
//    override fun getSearchHistories() {
//        val searchHistories = movieRepository.getSearchHistories()
//
//        if (searchHistories.isEmpty()) {
//            view.showNoResult()
//        } else {
//            view.showSearchHistoryItems(searchHistories)
//        }
//    }
//
//    override fun searchMovies(query: String) {
//        view.searchMovies(query)
//    }
//
//}