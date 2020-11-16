package com.example.androidarchitecturestudy.ui

import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.repository.NaverRepository

class MainPresenter(private val view: MainContract.View, private val naverRepository: NaverRepository) :MainContract.Presenter{

    override fun requestMovieInfo(title: String) = if(title.isEmpty()){
        view.showQueryEmpty()
    } else {
        view.showLoadingBar()
        naverRepository.getSearchMovieList(title,
            {
                view.showResult(it)
                view.hideLoadingBar()
            },{
                view.showResultEmpty(it)
                view.hideLoadingBar()
            })
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