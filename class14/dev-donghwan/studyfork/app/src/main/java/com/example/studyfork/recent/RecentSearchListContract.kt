package com.example.studyfork.recent

interface RecentSearchListContract {
    interface View {
        fun showListIsEmpty()
        fun showRecentSearchList(list: List<String>)
    }
    interface Presenter {
        fun getRecentSearchList()
    }
}