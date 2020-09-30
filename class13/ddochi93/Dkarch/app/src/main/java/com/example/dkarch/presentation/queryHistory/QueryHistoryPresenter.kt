package com.example.dkarch.presentation.queryHistory

import com.example.dkarch.domain.repository.NaverMovieRepository
import com.example.dkarch.presentation.base.BasePresenter

class QueryHistoryPresenter(
    override val view: QueryHistoryContract.View, private val naverMovieRepository: NaverMovieRepository
) : QueryHistoryContract.Presenter, BasePresenter(view) {
    override fun getSavedQueryList() {
        view.showQueryList(naverMovieRepository.getQueryList())
    }
}
