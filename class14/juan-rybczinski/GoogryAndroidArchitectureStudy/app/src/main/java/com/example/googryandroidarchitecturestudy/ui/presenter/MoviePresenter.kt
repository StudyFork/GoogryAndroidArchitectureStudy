package com.example.googryandroidarchitecturestudy.ui.presenter

import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.ui.MovieContract
import java.lang.Exception

class MoviePresenter(
    private val view: MovieContract.View,
    private val repository: MovieRepository
) : MovieContract.Presenter {
    override suspend fun queryMovieList(query: String) {
        try {
            if (query.isEmpty()) {
                view.showQueryEmpty()
                return
            }
            val movieList = repository.searchMovies(query)
            if (movieList.isEmpty()) {
                view.showNoMovieSearchResult()
                return
            }
            view.hideKeyboard()
            view.showMovieList(movieList)
        } catch (e: Exception) {
            view.showMovieSearchFailed()
        }
    }

    override fun selectMovieItem(item: Movie) {
        view.showMovieDetail(item)
    }
}