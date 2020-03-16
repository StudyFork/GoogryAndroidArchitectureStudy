package com.mtjin.androidarchitecturestudy.ui.login

import android.content.Context

interface LoginContract {
    interface View {
        fun showLoginErrorToast()
        fun showIdEmptyError()
        fun showPwEmptyError()
        fun finishActivity()
        fun goMovieSearchActivity()
        fun saveAutoLoginSharedPref()
    }

    interface Presenter {
        fun login(id: String, pw: String)
    }

}