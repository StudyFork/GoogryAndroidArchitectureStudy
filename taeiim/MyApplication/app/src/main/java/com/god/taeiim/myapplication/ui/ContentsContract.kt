package com.god.taeiim.myapplication.ui

import com.god.taeiim.myapplication.Tabs
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

        fun searchContents(searchType: Tabs, query: String)

        fun getLastSearchHistory(searchType: Tabs)

        fun searchResultShowWrapper(
            searchType: Tabs,
            searchResult: SearchResult
        ): SearchResultShow
    }
}