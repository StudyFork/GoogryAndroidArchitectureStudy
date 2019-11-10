package com.practice.achitecture.myproject.main

import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSource
import com.practice.achitecture.myproject.data.source.remote.NaverRepository
import com.practice.achitecture.myproject.model.SearchedItem


class MainPresenter(
    private val view: MainContract.View,
    private val repository: NaverRepository
) : MainContract.Presenter {

    override fun searchWordByNaver(category: String, word: String) {
        repository.searchWordByNaver(
            category,
            word,
            object : NaverRemoteDataSource.GettingResultOfSearchingCallBack {

                override fun onSuccess(items: List<SearchedItem>) {
                    view.searchingOnSuccess(items)
                }

                override fun onFailure(throwable: Throwable) {
                    view.searchingOnFailure(throwable)
                }
            })
    }

}