package kr.dktsudgg.androidarchitecturestudy.data.datasource.local

import android.app.Application
import android.content.Context

object SharedPreferencesTool {

    private lateinit var appCtx: Context

    fun init(applicationContext: Application) {
        appCtx = applicationContext
    }

    fun putData(prefFileKeyResId: Int, putKey: Int, data: String) {
        appCtx.getSharedPreferences(
            appCtx.getString(prefFileKeyResId),
            Context.MODE_PRIVATE
        ).edit().putString(
            appCtx.getString(putKey), data
        ).commit()
    }

    fun getData(prefFileKeyResId: Int, getKey: Int): String? {
        return appCtx.getSharedPreferences(
            appCtx.getString(prefFileKeyResId),
            Context.MODE_PRIVATE
        ).getString(appCtx.getString(getKey), null)
    }

}