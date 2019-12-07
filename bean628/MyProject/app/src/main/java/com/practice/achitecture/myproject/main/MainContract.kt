package com.practice.achitecture.myproject.main

import com.practice.achitecture.myproject.base.BaseContract
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.model.SearchedItem

interface MainContract {

    interface View : BaseContract.View {
        fun showSearchResultBlogOrNews(items: List<SearchedItem>)
        fun showSearchResultMovieOrBook(items: List<SearchedItem>)
        fun searchingOnFailure(throwable: Throwable)
        fun isEmpty()
    }

    interface Presenter : BaseContract.Presenter {
        fun searchWordByNaver(searchType: SearchType, category: String, word: String)
        fun searchIfNotEmpty(word: String, searchType: SearchType)
    }

}