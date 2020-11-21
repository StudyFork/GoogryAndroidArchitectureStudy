package com.example.googryandroidarchitecturestudy.ui.presenter

import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.ui.contract.MovieContract

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
            val movieList = repository.searchMovies(query)
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