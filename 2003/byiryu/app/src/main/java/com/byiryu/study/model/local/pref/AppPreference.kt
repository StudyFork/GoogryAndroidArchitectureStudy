package com.byiryu.study.model.local.pref

import android.content.Context

class AppPreference constructor(context: Context) {

    companion object {
        const val APP_PREF_NAME = "1234-1234-1234-1234"

        const val PREF_QUERY = "aaaa-bbbb-cccc-dddd"

        const val PREF_AUTO_LOGIN = "A1B2-C3D4-E5F6-G7H8"
    }

    private val pref =
        context.applicationContext.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE)

    fun getPrevQuery(): String {
        return pref.getString(PREF_QUERY, "")!!
    }

    fun setPrevQuery(query: String) {
        pref.edit().putString(PREF_QUERY, query).apply()
    }

    fun setAutoLogin() {
        pref.edit().putBoolean(PREF_AUTO_LOGIN, true).apply()
    }

    fun getAutoLogin(): Boolean {
        return pref.getBoolean(PREF_AUTO_LOGIN, false)

    }


}