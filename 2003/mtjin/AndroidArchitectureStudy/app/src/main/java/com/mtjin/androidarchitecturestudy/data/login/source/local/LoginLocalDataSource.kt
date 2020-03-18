package com.mtjin.androidarchitecturestudy.data.login.source.local

interface LoginLocalDataSource {
    fun getAutoLogin(): Boolean
    fun saveAutoLogin()
}