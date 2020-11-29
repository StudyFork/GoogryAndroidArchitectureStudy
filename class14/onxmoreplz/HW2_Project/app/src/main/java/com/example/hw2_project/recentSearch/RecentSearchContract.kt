package com.example.hw2_project.recentSearch

interface RecentSearchContract {
    interface View {
        fun showRecentSearchMovieList(list: List<String>)
    }

    interface Presenter {
        fun searchRecentQuery()
    }
}