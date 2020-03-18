package com.mtjin.androidarchitecturestudy.ui.login

interface LoginContract {
    interface View {
        fun showLoginErrorToast()
        fun showIdEmptyError()
        fun showPwEmptyError()
        fun goMovieSearchActivity()
        fun saveAutoLoginSharedPref()
    }

    interface Presenter {
        fun login(id: String, pw: String)
    }

}