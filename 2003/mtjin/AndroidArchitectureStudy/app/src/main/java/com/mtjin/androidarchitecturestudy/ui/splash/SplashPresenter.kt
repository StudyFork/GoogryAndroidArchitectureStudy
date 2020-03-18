package com.mtjin.androidarchitecturestudy.ui.splash

class SplashPresenter(private val view: SplashContract.View) : SplashContract.Presenter {
    override fun doSplash() {
        if (view.checkNetworkState()) {
            view.showAutoLogin()
            view.goMovieSearch()
        } else {
            view.goLogin()
        }
    }
}