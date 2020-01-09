package app.ch.study.data.remote.api

import android.content.Context
import android.util.Log
import app.ch.study.R

object WebApiDefine {

    fun HOST_IP(context: Context): String = context.getString(R.string.server_url)

    const val CONNECT_TIMEOUT = 15L
    const val WRITE_TIMEOUT = 15L
    const val READ_TIMEOUT = 15L

    //오류
    private const val ERROR_UNAUTHORIZED_ACCESS = 401  //허가되지않은 접속
    private const val ERROR_REQUIRING_UPDATE = 403  //업데이트 필요
    private const val ERROR_FROM_SERVER = 500  //서버오류
    private const val ERROR_SERVER_CHECKING = 503  //서버점검/업데이트중

    fun showErrorLog(errorCode: Int) {
        val errorString = when (errorCode) {
            ERROR_UNAUTHORIZED_ACCESS -> "ERROR_UNAUTHORIZED_ACCESS"
            ERROR_REQUIRING_UPDATE -> "ERROR_REQUIRING_UPDATE"
            ERROR_FROM_SERVER -> "ERROR_FROM_SERVER"
            ERROR_SERVER_CHECKING -> "ERROR_SERVER_CHECKING"
            else -> "UNDEFINED_ERROR"
        }

        Log.e("WebErrorLog", errorString)
    }

}





