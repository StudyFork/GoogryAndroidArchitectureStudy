package com.byiryu.local.model.data
import android.content.Context

class AppPreference constructor(context: Context) {

    companion object {
        const val APP_PREF_NAME = "com.byiryu.study"

        const val PREF_QUERY = "PREV_QUERY"

        const val PREF_AUTO_LOGIN = "AUTO_LOGIN"
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

    fun isAutoLogin(): Boolean {
        return pref.getBoolean(PREF_AUTO_LOGIN, false)

    }


}