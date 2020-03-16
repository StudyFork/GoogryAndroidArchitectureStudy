package com.mtjin.androidarchitecturestudy.ui.login

import android.content.Context

interface LoginContract {
    interface View {
        fun showLoginErrorToast()
        fun showIdEmptyError()
        fun showPwEmptyError()
        fun finishActivity()
    }

    interface Presenter {
        fun login(context: Context, id: String, pw: String)
    }

}