package app.ch.study.data.remote.api

import app.ch.study.BuildConfig
import app.ch.study.data.remote.parse.Authorization

class WebApiParams : Authorization {
    override fun getClientToken(): String =  BuildConfig.CLIENT_ID
    override fun getClientSecret(): String = BuildConfig.CLIENT_SECRET
}