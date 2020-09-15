package com.example.myproject.ui

import com.example.myproject.data.repository.NaverRepository
import com.example.myproject.ui.base.BasePresenter

class MainPresenter(private val view: MainContract.View, private val naverRepository: NaverRepository) :
    BasePresenter(view, naverRepository), MainContract.Presenter {
    override fun searchMovieList(title: String) {
        if (title.isEmpty()) {
            view.showQueryEmpty()
        } else {
            view.initScroll()
            naverRepository.getMovieList(title, {
                if (it.isEmpty()) {
                    view.showResultEmpty()
                } else {
                    view.showResult(it)
                }

            }, {
                view.networkError(it)
            })
        }
    }

}
