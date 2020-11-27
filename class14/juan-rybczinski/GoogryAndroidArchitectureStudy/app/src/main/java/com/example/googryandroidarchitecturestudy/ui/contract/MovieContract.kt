package com.example.googryandroidarchitecturestudy.ui.contract

interface MovieContract {

    interface View : MovieListContract.View {
        fun navToRecentSearch()
    }

    interface Presenter : MovieListContract.Presenter {

    }

}