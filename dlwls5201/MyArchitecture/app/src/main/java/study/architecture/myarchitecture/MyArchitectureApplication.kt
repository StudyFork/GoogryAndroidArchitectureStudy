package study.architecture.myarchitecture

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

class MyArchitectureApplication : Application() {

    companion object {

        var DEBUG = false

    }

    override fun onCreate() {
        super.onCreate()

        DEBUG = isDebuggable(this)
    }

    private fun isDebuggable(context: Context): Boolean {

        var debuggable = false

        val pm = context.packageManager

        try {
            val appInfo = pm.getApplicationInfo(context.packageName, 0)
            debuggable = 0 != appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        } catch (e: PackageManager.NameNotFoundException) {
            /* debuggable variable will remain false */
        }

        return debuggable
    }
}