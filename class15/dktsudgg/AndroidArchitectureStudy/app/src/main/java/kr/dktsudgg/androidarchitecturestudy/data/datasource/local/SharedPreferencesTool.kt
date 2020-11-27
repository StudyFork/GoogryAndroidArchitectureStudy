package kr.dktsudgg.androidarchitecturestudy.data.datasource.local

import android.app.Application
import android.content.Context

object SharedPreferencesTool {

    private lateinit var appCtx: Context

    fun init(applicationContext: Application) {
        appCtx = applicationContext
    }

    fun putData(prefFileKey: String, putKey: String, data: String) {
        appCtx.getSharedPreferences(
            prefFileKey,
            Context.MODE_PRIVATE
        ).edit().putString(
            putKey, data
        ).commit()
    }

    fun getData(prefFileKey: String, getKey: String): String {
        return appCtx.getSharedPreferences(
            prefFileKey,
            Context.MODE_PRIVATE
        ).getString(getKey, null)
            ?: ""
    }

}