package com.mtjin.local.source.login

import com.mtjin.data.source.login.local.LoginLocalDataSource
import com.mtjin.local.utils.PreferenceManager

internal class LoginLocalDataSourceImpl(private val preferenceManager: PreferenceManager) :
    LoginLocalDataSource {
    override var autoLogin: Boolean
        get() = preferenceManager.autoLogin
        set(value) {
            preferenceManager.autoLogin = value
        }
}