package com.god.taeiim.myapplication.ui

import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.api.model.SearchResultShow
import com.god.taeiim.myapplication.base.BasePresenter
import com.god.taeiim.myapplication.base.BaseView

interface ContentsContract {
    interface View : BaseView<Presenter> {

        fun failToSearch()

        fun blankSearchQuery()

        fun updateItems(resultList: List<SearchResultShow.Item>)

        fun updateSearchHistoryItems()

    }

    interface Presenter : BasePresenter {

        fun searchContents(searchType: String, query: String)

        fun getLastSearchHistory(searchType: String)

        fun searchResultShowWrapper(
            searchType: String,
            searchResult: SearchResult
        ): SearchResultShow
    }
}