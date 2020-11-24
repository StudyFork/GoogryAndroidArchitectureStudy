package com.example.androidarchitecturestudy.ui

interface TitleHistoryContract {
    interface View{
        fun setTitleList(list: ArrayList<String>)
    }

    interface Presenter{
        fun getRecentTitleList()
    }
}