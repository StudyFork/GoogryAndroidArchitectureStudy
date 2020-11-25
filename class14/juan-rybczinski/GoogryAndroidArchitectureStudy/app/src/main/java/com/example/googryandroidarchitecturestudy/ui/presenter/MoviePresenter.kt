package com.example.googryandroidarchitecturestudy.ui.presenter

import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
import com.example.googryandroidarchitecturestudy.ui.contract.MovieContract
import java.util.*

class MoviePresenter(
    private val view: MovieContract.View,
    private val repository: MovieRepository
) : BasePresenter(view), MovieContract.Presenter {
    override suspend fun queryMovieList(query: String) {
        if (query.isEmpty()) {
            view.showQueryEmpty()
            return
        }
        try {
            view.showProgressBar()
            repository.insertRecent(RecentSearch(
                Date(System.currentTimeMillis()),
                query
            ))
            val movieList = repository.searchMovies(query)
            view.hideProgressBar()
            if (movieList.isEmpty()) {
                view.showNoSearchResult()
                return
            }
            view.hideKeyboard()
            view.showMovieList(movieList)
        } catch (e: Exception) {
            view.showSearchFailed(e)
        }
    }
}