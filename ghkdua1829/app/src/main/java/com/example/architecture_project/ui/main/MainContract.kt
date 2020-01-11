package com.example.architecture_project.ui.main

import com.example.architecture_project.data.model.NaverApi

interface MainContract {
    interface View {
        fun showNoResult()
        fun showNotAvailableKeyword()
        fun showResult(data: NaverApi)
        fun showNotExistKeyword()
        fun showDataNum(num:Int)
    }

    interface Presenter {
        fun getMovieData(keyword: String)
        fun getMovieDataNum(data:NaverApi)
    }
}