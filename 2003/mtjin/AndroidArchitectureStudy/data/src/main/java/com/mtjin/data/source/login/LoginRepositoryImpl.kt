package com.mtjin.data.source.login

import com.mtjin.local.login.LoginLocalDataSource

internal class LoginRepositoryImpl(private val loginLocalDataSource: LoginLocalDataSource) :
    LoginRepository {

    override var autoLogin: Boolean
        get() = loginLocalDataSource.autoLogin
        set(value) {
            loginLocalDataSource.autoLogin = value
        }
}