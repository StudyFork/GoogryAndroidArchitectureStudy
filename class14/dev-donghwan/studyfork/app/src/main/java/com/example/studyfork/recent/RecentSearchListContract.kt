package com.example.studyfork.recent

interface RecentSearchListContract {
    interface View {
        fun showRecentSearchList(list: List<String>)
    }
    interface Presenter {
        fun getRecentSearchList()
    }
}