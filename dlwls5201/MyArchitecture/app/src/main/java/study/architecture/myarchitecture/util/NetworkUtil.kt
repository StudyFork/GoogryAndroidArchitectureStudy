package study.architecture.myarchitecture.util

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {

    fun isOnline(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo.isConnected
    }
}
