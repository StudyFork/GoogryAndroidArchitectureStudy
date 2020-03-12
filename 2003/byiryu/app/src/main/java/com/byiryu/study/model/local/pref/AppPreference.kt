package com.byiryu.study.model.local.pref

import android.content.Context

class AppPreference constructor(context: Context) {

    companion object {
        const val APP_PREF_NAME = "1234-1234-1234-1234"

        const val PREF_QUERY = "aaaa-bbbb-cccc-dddd"
    }

    private val pref =
        context.applicationContext.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE)

    fun getPrevQuery(): String {
        return pref.getString(PREF_QUERY, "")!!
    }

    fun setPrevQuery(query: String) {
        pref.edit().putString(PREF_QUERY, query).apply()
    }


}