package com.example.handnew04.ui.main

import com.example.handnew04.ui.base.BaseContract

interface MainContract : BaseContract {
    interface View {
        fun showEmptyResult()

        fun showInputLengthZero()

        fun showNotConnectedNetwork()

        fun showSuccessSearchMovie()

        fun showMovieDetailActivity()
    }

    interface Presenter {
        fun serchMovie()

        fun checkInputQuery()

        fun checkConnectedNetwork()
    }
}