package com.wybh.androidarchitecturestudy.history

import com.wybh.androidarchitecturestudy.CinemaItem

interface RecentSearchWordContract {
    interface View {
        fun showCinemaList(dataList: List<CinemaItem>)
        fun setSearchHistoryWord(wordList: List<String>)
        fun showToastFailMessage(message: String?)
    }

    interface Presenter {
        fun getSearchWord()
        fun searchCinema(query: String)
    }
}