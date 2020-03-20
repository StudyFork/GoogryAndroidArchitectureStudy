package com.mtjin.androidarchitecturestudy.ui.login

interface LoginContract {
    interface View {
        fun showLoginError()
        fun showIdEmptyError()
        fun showPwEmptyError()
        fun goMovieSearch()
    }

    interface Presenter {
        fun doLogin(id: String, pw: String)
    }

}