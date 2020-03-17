package com.example.kangraemin.ui.login

import com.example.kangraemin.base.KangBasePresenter
import com.example.kangraemin.model.AuthRepository

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
        fun startMainActivity()
    }

    interface Presenter : KangBasePresenter {
        fun checkIdIsEmpty(id: String, hasFocus: Boolean)
        fun checkPasswordIsEmpty(password: String, hasFocus: Boolean)
        fun checkLoginInfoHasEntered(id: String, password: String): Boolean
        fun checkAutoLoginStatus()
        fun activateButton(allValueEntered: Boolean)
        fun addAutoLoginStatus(authRepository: AuthRepository)
        fun login(id: String, password: String)
    }
}