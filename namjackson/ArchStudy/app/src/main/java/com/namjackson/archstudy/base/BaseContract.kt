package com.namjackson.archstudy.base

interface BaseContract {

    interface View {
        fun showError(errorMsg: String)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter

}