package com.example.androidarchitecturestudy.ui

import com.example.androidarchitecturestudy.data.repository.NaverRepository

class TitleHistoryPresenter(
    private val view: TitleHistoryContract.View,
    private val repositoryImpl: NaverRepository
) : TitleHistoryContract.Presenter {
    override fun getRecentTitleList() {
        val titleList = repositoryImpl.getMovieTitleList()
        titleList?.let { view.setTitleList(it as ArrayList<String>) }
    }
}