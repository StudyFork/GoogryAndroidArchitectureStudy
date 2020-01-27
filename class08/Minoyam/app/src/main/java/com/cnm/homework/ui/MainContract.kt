package com.cnm.homework.ui

import android.content.Context
import com.cnm.homework.data.model.NaverResponse

interface MainContract {

    interface View {
        fun showProgress()

        fun hideProgress()

        fun setItem(items: List<NaverResponse.Item>)

        fun showErrorEmptyQuery()

        fun showErrorEmtpyResult()

        fun getContext(): Context

    }

    interface Presenter {
        fun movieListSearch(query: String)

        fun disposableClear()

        fun loadMovieList(): List<NaverResponse.Item>
    }

}