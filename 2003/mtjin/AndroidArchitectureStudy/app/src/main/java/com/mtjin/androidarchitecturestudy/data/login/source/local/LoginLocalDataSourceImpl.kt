package com.mtjin.androidarchitecturestudy.data.login.source.local

import com.mtjin.androidarchitecturestudy.utils.PreferenceManager

class LoginLocalDataSourceImpl(private val preferenceManager: PreferenceManager) :
    LoginLocalDataSource {

    override fun getAutoLogin(): Boolean = preferenceManager.getBoolean()
    
    override fun saveAutoLogin() {
        preferenceManager.setBoolean(true)
    }
}