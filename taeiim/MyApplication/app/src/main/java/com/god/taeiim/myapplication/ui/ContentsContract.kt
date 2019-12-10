package com.god.taeiim.myapplication.ui

import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.base.BaseView

interface ContentsContract {
    interface View : BaseView<Presenter> {
        fun failToSearch()
        fun blankSearchQuery()
        fun updateItems(resultList: SearchResult)

    }

    interface Presenter {
        fun searchContents(searchType: String, query: String)

    }
}