package com.mtjin.androidarchitecturestudy.ui.splash

import androidx.databinding.ObservableBoolean
import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepository

class SplashViewModel(private val loginRepository: LoginRepository) {
    var goMovieSearch: ObservableBoolean = ObservableBoolean(false)
    var goLogin: ObservableBoolean = ObservableBoolean(false)

    fun doSplash() {
        if (loginRepository.autoLogin) {
            goMovieSearch.set(true)
        } else {
            goLogin.set(true)
        }
    }
}