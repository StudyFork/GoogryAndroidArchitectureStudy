package com.mtjin.androidarchitecturestudy.ui.splash

import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepository

class SplashPresenter(
    private val view: SplashContract.View,
    private val loginRepository: LoginRepository
) : SplashContract.Presenter {
    init {
        doSplash()
    }

    private fun doSplash() {
        if (loginRepository.getAutoLogin()) {
            view.showAutoLogin()
            view.goMovieSearch()
        } else {
            view.goLogin()
        }
    }
}