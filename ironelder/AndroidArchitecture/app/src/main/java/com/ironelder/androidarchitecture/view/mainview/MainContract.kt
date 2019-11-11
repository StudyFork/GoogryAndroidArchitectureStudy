package com.ironelder.androidarchitecture.view.mainview

interface MainContract {
    interface View{
        fun onDataChanged()
        fun showErrorMessage()
        fun showLoading()
        fun hideLoading()
        fun openBrowser()
    }
    interface Presenter {
        fun search()
        fun clickItem()
    }
}