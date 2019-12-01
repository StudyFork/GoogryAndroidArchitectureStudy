package com.practice.achitecture.myproject.main

import com.practice.achitecture.myproject.BasePresenter
import com.practice.achitecture.myproject.BaseView
import com.practice.achitecture.myproject.model.SearchedItem

interface MainContract {

    interface View :
        BaseView<Presenter> {
        fun showSearchResultBlogOrNews(items: List<SearchedItem>)
        fun showSearchResultMovieOrBook(items: List<SearchedItem>)
        fun searchingOnFailure(throwable: Throwable)
        fun isEmpty()
    }

    interface Presenter : BasePresenter {
        fun searchWordByNaver(searchType: Int, category: String, word: String)
        fun searchIfNotEmpty(word: String, searchType: Int)
    }

}