package com.practice.achitecture.myproject.main

import com.practice.achitecture.myproject.BasePresenter
import com.practice.achitecture.myproject.BaseView
import com.practice.achitecture.myproject.model.SearchedItem

interface MainContract {

    interface View :
        BaseView<Presenter> {
        fun searchingOnSuccess(items: List<SearchedItem>)
        fun searchingOnFailure(throwable: Throwable)
    }

    interface Presenter : BasePresenter {

        fun searchWordByNaver(category: String, word: String)

    }

}