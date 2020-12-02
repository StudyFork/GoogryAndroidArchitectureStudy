package com.example.androidarchitecturestudy.presenter

import com.example.androidarchitecturestudy.data.repository.MovieRepository
import com.example.androidarchitecturestudy.room.SearchedDataBase

class SearchedMoviePresenter(
    private val view: SearchedMovieContract.View,
    private val repository: MovieRepository
) : SearchedMovieContract.Presenter {
    override fun getSearchedMovieList(database: SearchedDataBase) {
        val searchedMovieList = repository.getRecentSearch(database)
        view.updateRecyclerView(searchedMovieList)
    }
}
