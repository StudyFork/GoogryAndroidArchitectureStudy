package com.mtjin.androidarchitecturestudy.ui.splash

interface SplashContract {
    interface View {
        fun showAutoLoginToast()
        fun goLoginActivity()
        fun goMovieSearchActivity()
        fun checkNetworkState() : Boolean
    }

    interface Presenter {
        fun splashStart()
    }
}