package com.example.architecturestudy.ui.movie

import com.example.architecturestudy.Injection

class MoviePresenter(val view : MovieContract.View) : MovieContract.Presenter {

        private val repository by lazy { Injection.provideNaverSearchRepository() }

        override fun taskSearch(keyword: String) {
                repository.getMovie(
                        keyword = keyword,
                        success = {view.showResult(it)},
                        fail = {e -> view.showErrorMessage(e.toString())}
                )
        }
}