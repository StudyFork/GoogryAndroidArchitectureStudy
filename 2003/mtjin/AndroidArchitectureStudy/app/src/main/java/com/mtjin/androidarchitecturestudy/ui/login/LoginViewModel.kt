package com.mtjin.androidarchitecturestudy.ui.login

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) {
    var id: ObservableField<String> = ObservableField("")
    var pw: ObservableField<String> = ObservableField("")
    var isIdEmpty: ObservableBoolean = ObservableBoolean(false)
    var isPwEmpty: ObservableBoolean = ObservableBoolean(false)
    var loginErrorMsg: ObservableBoolean = ObservableBoolean(false)
    var successLogin: ObservableBoolean = ObservableBoolean(false)

    fun onLoginClick() {
        val id = id.get().toString().trim()
        val pw = pw.get().toString().trim()
        if (id.isEmpty()) {
            isIdEmpty.notifyChange()
        } else if (pw.isEmpty()) {
            isPwEmpty.notifyChange()
        } else if (id != USER_ID || pw != USER_PW) {
            loginErrorMsg.notifyChange()
        } else {
            loginRepository.autoLogin = true
            successLogin.notifyChange()
        }
    }

    companion object {
        private const val USER_ID = "id"
        private const val USER_PW = "P@sswOrd"
    }
}