package com.mtjin.androidarchitecturestudy.ui.splash

interface SplashContract {
    interface View {
        fun showAutoLogin()
        fun goLogin()
        fun goMovieSearch()
    }

    interface Presenter
}