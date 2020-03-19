package com.mtjin.androidarchitecturestudy.data.login.source

import com.mtjin.androidarchitecturestudy.data.login.source.local.LoginLocalDataSource

class LoginRepositoryImpl(private val loginLocalDataSource: LoginLocalDataSource) :
    LoginRepository {

    override var autoLogin: Boolean
        get() = loginLocalDataSource.autoLogin
        set(value) {
            loginLocalDataSource.autoLogin = value
        }
}