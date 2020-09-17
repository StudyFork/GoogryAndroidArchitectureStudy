package com.hjhan.hyejeong.ui

import com.hjhan.hyejeong.data.repository.NaverRepository
import com.hjhan.hyejeong.ui.base.BasePresenter

class MainPresenter(
    private val view: MainContract.View,
    private val repositoryImpl: NaverRepository
) : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun getMovieList(query: String) {
        if (query.isBlank()) {
            view.emptyQuery()
            return
        } else {
            repositoryImpl.getSearchMovies(
                query = query,
                success = {
                    it.items.run {

                        if (it.items.isEmpty()) {
                            view.emptyMovieList()
                        } else {
                            view.setMovieList(it.items)
                        }
                    }
                },
                failed = {
                    view.showError(it)
                })
        }
    }
}