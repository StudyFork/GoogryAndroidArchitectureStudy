package com.example.androidarchitecturestudy.ui

import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.repository.NaverRepository

abstract class BasePresenter(
    private val view: BaseContract.View,
    private val naverRepository: NaverRepository
) : BaseContract.Presenter {

    override fun saveMovieData(movie: List<Movie>) {
        naverRepository.saveMovieData(movie)
    }

    override fun requestLocalMovieData() {
        naverRepository.getMovieData()?.let { view.getMovieData(it) }
    }
}