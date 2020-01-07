package app.ch.study.data.remote.api

import android.content.Context
import app.ch.study.R
import app.ch.study.data.remote.parse.Authorization

class WebApiParams(private val context: Context?) : Authorization {
    override fun getClientToken(): String? =  context?.getString(R.string.client_id)
    override fun getClientSecret(): String? = context?.getString(R.string.client_secret)
}