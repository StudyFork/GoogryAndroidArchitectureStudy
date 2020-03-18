package com.mtjin.androidarchitecturestudy.ui.splash

interface SplashContract {
    interface View {
        fun showAutoLoginToast()
        fun goLogin()
        fun goMovieSearch()
        fun checkNetworkState() : Boolean
    }

    interface Presenter {
        fun splashStart()
    }
}