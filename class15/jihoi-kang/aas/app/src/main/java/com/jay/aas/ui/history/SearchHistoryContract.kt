package com.jay.aas.ui.history

import com.jay.aas.base.BaseContract
import com.jay.aas.model.SearchHistory

interface SearchHistoryContract {

    interface View : BaseContract.View {
        fun showNoResult()
        fun showSearchHistoryItems(searchHistories: List<SearchHistory>)
        fun searchMovies(query: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun getSearchHistories()
        fun searchMovies(query: String)
    }

}