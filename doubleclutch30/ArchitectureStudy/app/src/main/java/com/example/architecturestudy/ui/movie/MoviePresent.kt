package com.example.architecturestudy.ui.movie

import com.example.architecturestudy.Injection

class MoviePresent(
        val view : MovieContract.View, private val adapter: MovieAdapter
) : MovieContract.Present {

        private val repository by lazy { Injection.provideNaverSearchRepository() }

        override fun searchMovie(keyword: String) {
                repository.getMovie(
                        keyword = keyword,
                        success = {adapter.update(it)},
                        fail = {e -> taskError(e)}
                )
        }

        override fun taskError(error: Throwable) {
                val msg = error.message.toString()
                view.showErrorMessage(msg)
        }
}