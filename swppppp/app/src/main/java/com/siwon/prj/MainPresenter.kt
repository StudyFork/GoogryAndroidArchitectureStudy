package com.siwon.prj

import com.siwon.prj.repository.MovieSearchRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter, KoinComponent {

    val repository: MovieSearchRepository by inject()

    override fun getSearchResult(input: String) {
        repository.searchMovies(input,
            success = {},
            fail = {})
    }
}