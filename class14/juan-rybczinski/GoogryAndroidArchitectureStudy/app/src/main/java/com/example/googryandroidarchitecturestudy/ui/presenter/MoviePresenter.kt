package com.example.googryandroidarchitecturestudy.ui.presenter

import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.ui.contract.MovieContract

class MoviePresenter(
    private val view: MovieContract.View,
    private val repository: MovieRepository
) : BasePresenter(), MovieContract.Presenter {
    override suspend fun queryMovieList(query: String) {
        if (query.isEmpty()) {
            view.showQueryEmpty()
            return
        }
        try {
            val movieList = repository.searchMovies(query)
            if (movieList.isEmpty()) {
                view.showNoMovieSearchResult()
                return
            }
            view.hideKeyboard()
            view.showMovieList(movieList)
        } catch (e: Exception) {
            view.showMovieSearchFailed(e)
        }
    }

    override fun selectMovieItem(item: Movie) {
        view.showMovieDetail(item)
    }
}