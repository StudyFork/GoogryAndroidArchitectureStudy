package com.practice.achitecture.myproject.history

import androidx.annotation.StringRes
import com.practice.achitecture.myproject.base.BaseContract
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.model.SearchedItem

interface HistoryContract {
    interface View : BaseContract.View {
        fun showSearchResultBlogOrNews(items: List<SearchedItem>)
        fun showSearchResultMovieOrBook(items: List<SearchedItem>)
        fun historyEmpty(@StringRes stringId: Int)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadHistory(searchType: SearchType)
    }
}