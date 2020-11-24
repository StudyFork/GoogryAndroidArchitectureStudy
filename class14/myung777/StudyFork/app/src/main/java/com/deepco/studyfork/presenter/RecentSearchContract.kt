package com.deepco.studyfork.presenter


interface RecentSearchContract {
    interface View : BaseContract.View {
        fun setRecentSearchList(list: List<String>)
        fun onRecentSearchMovie(query: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun setRecentSearchList()
        fun getRecentSearchMovie(query: String)

    }
}