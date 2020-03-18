package com.mtjin.androidarchitecturestudy.data.login.source

interface LoginRepository {

    fun getAutoLogin(): Boolean
    fun saveAutoLogin()
}