package com.example.aas.ui.main

import com.example.aas.base.BasePresenter
import com.example.aas.data.model.ApiResult
import com.example.aas.data.repository.MovieSearchRepository
import io.reactivex.Single

class MainPresenter(
    override val view: MainContract.View,
    private val movieSearchRepository: MovieSearchRepository
) : BasePresenter(view), MainContract.Presenter {

    override fun getMovies(query: String): Single<ApiResult> {
        view.onSearchRequest()
        return movieSearchRepository.getMovies(query)
    }

    override fun getSavedQueries(): Single<List<String>> =
        movieSearchRepository.getSavedQueries()

    override fun onDestroy() {
        movieSearchRepository.onDestroy()
        super.onDestroy()
    }
}