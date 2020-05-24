package com.project.architecturestudy.ui.search

import com.project.architecturestudy.data.repository.NaverMovieRepository

class SearchPresenter(
    private val view: SearchContract.View,
    private val naverMovieRepository: NaverMovieRepository
) : SearchContract.Presenter {

    override fun validateSearchWord(searchWord: String) {
    }

    override fun getMovieListFromLocal() {
    }

    override fun getMovieListFromRemote() {
    }
}