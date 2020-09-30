package com.example.dkarch.presentation.queryHistory

import com.example.dkarch.presentation.base.BaseContract

interface QueryHistoryContract {
    interface View : BaseContract.View {
        fun showQueryList(savedQueryList: List<String>)
    }

    interface Presenter : BaseContract.Presenter {
        fun getSavedQueryList()
    }
}
