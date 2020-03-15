package com.mtjin.androidarchitecturestudy.ui.login

import android.content.Context

interface LoginContract {
    interface View {
        fun showToast(msg: String)
        fun showIdError(msg: String)
        fun showPwError(msg: String)
        fun finishActivity()
    }

    interface Presenter {
        fun login(context: Context, id: String, pw: String)
    }

}