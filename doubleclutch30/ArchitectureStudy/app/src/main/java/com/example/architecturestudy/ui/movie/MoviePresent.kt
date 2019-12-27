package com.example.architecturestudy.ui.movie

import com.example.architecturestudy.Injection

class MoviePresent(
        private val view : MovieContract.View
) : MovieContract.MoviePresent {

        private lateinit var movieAdapter: MovieAdapter
        private val repository by lazy { Injection.provideNaverSearchRepository() }

        override fun searchMovie(keyword: String) {
                repository.getMovie(
                        keyword = keyword,
                        success = {movieAdapter.update(it)},
                        fail = {e -> taskError(e)}
                )
        }

        override fun taskError(error: Throwable) {
                val msg = error.message.toString()
                view.showErrorMessage(msg)
        }
}