package com.hjhan.hyejeong.ui

interface QueryHistoryContract {

    interface View {
        fun emptyQueryList()
        fun setRecentQueryList(list: List<String>)
    }

    interface Presenter {
        fun getRecentQueryList()
    }

}