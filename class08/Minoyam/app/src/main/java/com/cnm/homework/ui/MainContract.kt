package com.cnm.homework.ui

import com.cnm.homework.data.model.NaverResponse

interface MainContract {

    interface View {
        fun showProgress()

        fun hideProgress()

        fun setItem(items: List<NaverResponse.Item>)

        fun showErrorEmptyQuery()

        fun showErrorEmptyResult()

        fun showEmptyLayout()

        fun hideEmptyLayout()
    }

    interface Presenter {
        fun movieListSearch(query: String)

        fun disposableClear()

        fun loadMovieList(): List<NaverResponse.Item>
    }

}