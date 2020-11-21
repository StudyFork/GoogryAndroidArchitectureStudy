package com.example.androidarchitecturestudy.ui.main

import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.repository.NaverRepository
import com.example.androidarchitecturestudy.ui.base.BasePresenter

class MainPresenter(
    private val view: MainContract.View,
    private val naverRepository: NaverRepository
) : BasePresenter(view, naverRepository), MainContract.Presenter {

    override fun requestMovieInfo(title: String) {
        view.hideKeyboard()
        if (title.isEmpty()) {
            view.showQueryEmpty()
        } else {
            view.showLoadingBar()
            naverRepository.getSearchMovieList(title,
                {
                    view.showResult(it)
                    view.hideLoadingBar()
                }, {
                    view.showResultEmpty(it)
                    view.hideLoadingBar()
                })
        }
    }

    override fun requestLocalMovieData() {
        naverRepository.getMovieData()?.let { view.getMovieData(it) }
    }

    override fun openMovieLink(link: String) {
        view.openMovieLink(link)
    }

    override fun saveMovieData(movie: List<Movie>) {
        naverRepository.saveMovieData(movie)
    }

}