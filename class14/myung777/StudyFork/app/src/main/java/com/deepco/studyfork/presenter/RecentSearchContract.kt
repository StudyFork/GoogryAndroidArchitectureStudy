package com.deepco.studyfork.presenter

import com.deepco.studyfork.data.model.RecentSearchData


interface RecentSearchContract {
    interface View : BaseContract.View {
        fun setRecentSearchList(list: List<RecentSearchData>)
        fun onRecentSearchMovie(query: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun setRecentSearchList()
        fun getRecentSearchMovie(query: String)

    }
}