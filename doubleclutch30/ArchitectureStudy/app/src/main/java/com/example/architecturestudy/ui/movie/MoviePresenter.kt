package com.example.architecturestudy.ui.movie

import com.example.architecturestudy.Injection

class MoviePresenter(val view : MovieContract.View) : MovieContract.Presenter {

        private val repository by lazy { Injection.provideNaverSearchRepository() }

        override fun searchMovie(keyword: String) {
                repository.getMovie(
                        keyword = keyword,
                        success = {view.showResult(it)},
                        fail = {e -> taskError(e)}
                )
        }

        override fun taskError(error: Throwable) {
                val msg = error.message.toString()
                view.showErrorMessage(msg)
        }
}