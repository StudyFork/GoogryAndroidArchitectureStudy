package com.architecture.androidarchitecturestudy.ui.searchhistory

import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.architecture.androidarchitecturestudy.ui.base.BaseContract

class SearchHistoryContract {
    interface View : BaseContract.View {
        fun setSearchHistoryList(list: List<SearchHistoryEntity>)
        fun onSearchHistoryMovie(keyword: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun setSearchHistoryList()
        fun getSearchHistoryMovie(keyword: String)
    }
}
