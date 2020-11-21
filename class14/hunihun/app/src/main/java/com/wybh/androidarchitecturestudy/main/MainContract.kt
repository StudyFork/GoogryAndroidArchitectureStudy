package com.wybh.androidarchitecturestudy.main

import com.wybh.androidarchitecturestudy.CinemaItem

interface MainContract {
    interface View {
        fun showCinemaList(dataList: List<CinemaItem>)
        fun showToastFailMessage(message: String?)
    }

    interface Presenter {
        fun searchCinema(query: String)
        fun removeCompositeDisposable()
    }
}