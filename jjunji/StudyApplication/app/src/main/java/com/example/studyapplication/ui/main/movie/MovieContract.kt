package com.example.studyapplication.ui.main.movie

import com.example.studyapplication.data.model.MovieInfo
import com.example.studyapplication.ui.main.base.BaseSearchContract

interface MovieContract {
    interface View : BaseSearchContract.View {
        fun showList(items: ArrayList<MovieInfo>)
        fun showEmptyView()
    }

    interface Presenter : BaseSearchContract.Presenter {
        fun checkCacheData()
        fun clickSearchButton(query: String)
    }
}