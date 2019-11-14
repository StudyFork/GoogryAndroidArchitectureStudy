package com.god.taeiim.myapplication.ui

import com.god.taeiim.myapplication.api.model.SearchResult

interface ContentsContract {
    interface View {
        fun failToSearch()
        fun updateItems(resultList: SearchResult)

    }

    interface Presenter {
        fun searchContents(searchType: String, query: String)

    }
}