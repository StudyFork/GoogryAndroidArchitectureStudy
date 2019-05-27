package com.namjackson.archstudy.base

interface BaseContract {

    interface View {
        fun showError(errorMsg: String)

        fun showLoading()

        fun hideLoading()
    }

    interface Presenter

}