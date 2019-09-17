package com.android.studyfork.ui.main.presenter


interface MainContract {
    interface View {
        fun setViewPagerData(titles: List<String>, items: List<String>)
    }

    interface Presenter {
        fun loadData()
    }

}