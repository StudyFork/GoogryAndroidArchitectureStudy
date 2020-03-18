package com.mtjin.androidarchitecturestudy.ui.splash

class SplashPresenter(private val view: SplashContract.View) : SplashContract.Presenter {
    override fun splashStart() {
        if (view.checkNetworkState()) {
            view.showAutoLoginToast()
            view.goMovieSearch()
        } else {
            view.goLogin()
        }
    }
}