package com.mtjin.androidarchitecturestudy.data.login.source

import com.mtjin.androidarchitecturestudy.data.login.source.local.LoginLocalDataSource

class LoginRepositoryImpl(private val loginLocalDataSource: LoginLocalDataSource) :
    LoginRepository {

    override fun getAutoLogin(): Boolean = loginLocalDataSource.autoLogin

    override fun saveAutoLogin() {
        loginLocalDataSource.autoLogin = true
    }
}