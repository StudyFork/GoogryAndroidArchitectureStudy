package com.siwon.prj.view.presenter

import com.siwon.prj.common.model.Movie

interface MainContract {
    interface View {
        fun showResult(result: ArrayList<Movie>)
        fun emptyInput()
        fun emptyResult()
        fun serviceErr(erMsg: String)
        fun hideKeyboard()
    }

    interface  Presenter {
        fun getSearchResult(input: String)
    }
}