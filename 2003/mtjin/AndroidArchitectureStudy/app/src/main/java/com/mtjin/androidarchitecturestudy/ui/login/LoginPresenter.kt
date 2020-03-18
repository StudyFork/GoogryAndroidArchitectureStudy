package com.mtjin.androidarchitecturestudy.ui.login

import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepository

class LoginPresenter(
    private val view: LoginContract.View,
    private val loginRepository: LoginRepository
) : LoginContract.Presenter {

    override fun doLogin(id: String, pw: String) {
        if (id.isEmpty()) {
            view.showIdEmptyError()
        } else if (pw.isEmpty()) {
            view.showPwEmptyError()
        } else if (id != USER_ID || pw != USER_PW) {
            view.showLoginError()
        } else {
            loginRepository.saveAutoLogin()
            view.goMovieSearch()
        }
    }

    companion object {
        private const val USER_ID = "id"
        private const val USER_PW = "P@sswOrd"
    }
}