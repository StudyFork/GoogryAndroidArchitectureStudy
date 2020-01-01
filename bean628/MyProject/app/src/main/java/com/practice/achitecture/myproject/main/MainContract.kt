package com.practice.achitecture.myproject.main

import com.practice.achitecture.myproject.base.BaseContract
import com.practice.achitecture.myproject.enums.SearchType
import com.practice.achitecture.myproject.model.SearchedItem

interface MainContract {

    interface View : BaseContract.View {
        fun showSearchResultBlogOrNews(items: List<SearchedItem>)
        fun showSearchResultMovieOrBook(items: List<SearchedItem>)
        fun searchingOnFailure(throwable: Throwable)
        fun searchWordEmpty()
    }

    interface Presenter : BaseContract.Presenter {
        fun searchWordByNaver(searchType: SearchType, word: String)
        fun searchIfNotEmpty(word: String, searchType: SearchType)
        fun loadCache()
    }

}