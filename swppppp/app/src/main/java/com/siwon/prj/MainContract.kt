package com.siwon.prj

interface MainContract {
    interface View {
        fun showResult()
        fun emptyInput()
        fun emptyResult()
    }

    interface  Presenter {
        fun getSearchResult(input: String)
    }
}