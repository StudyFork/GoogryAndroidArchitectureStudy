package com.example.kangraemin.ui.login

import com.example.kangraemin.base.KangBasePresenter

interface LoginContract {
    interface View {
        fun showEmptyIdError()
        fun hideEmptyIdError()
        fun showPasswordEmptyError()
        fun hidePasswordEmptyError()
        fun showFailedLoginError()
        fun hideFailedLoginError()
        fun enableLoginButton()
        fun disableLoginButton()
        fun startMain()
    }

    interface Presenter : KangBasePresenter {
        fun checkIdIsEmpty(id: String, hasFocus: Boolean)
        fun checkPasswordIsEmpty(password: String, hasFocus: Boolean)
        fun idTextChanges(id: String)
        fun pwTextChanges(pw: String)
        fun addAutoLoginStatus()
        fun login(id: String, password: String, isAutoLogin: Boolean)
    }
}