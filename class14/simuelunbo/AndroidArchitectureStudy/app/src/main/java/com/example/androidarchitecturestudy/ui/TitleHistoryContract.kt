package com.example.androidarchitecturestudy.ui

import com.example.androidarchitecturestudy.data.model.QueryHistory

interface TitleHistoryContract {
    interface View{
        fun setTitleList(list: ArrayList<QueryHistory>)
    }

    interface Presenter{
        fun getRecentTitleList()
    }
}