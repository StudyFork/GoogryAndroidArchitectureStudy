package study.architecture.myarchitecture.util

import android.util.Log
import study.architecture.myarchitecture.MyArchitectureApplication

object Dlog {

    val TAG = "BlackJin"

    /**
     * Log Level Error
     */
    fun e(message: String?) {
        if (MyArchitectureApplication.DEBUG) Log.e(TAG, buildLogMsg(message ?: ""))
    }

    /**
     * Log Level Warning
     */
    fun w(message: String?) {
        if (MyArchitectureApplication.DEBUG) Log.w(TAG, buildLogMsg(message ?: ""))
    }

    /**
     * Log Level Information
     */
    fun i(message: String?) {
        if (MyArchitectureApplication.DEBUG) Log.i(TAG, buildLogMsg(message ?: ""))
    }

    /**
     * Log Level Debug
     */
    fun d(message: String?) {
        if (MyArchitectureApplication.DEBUG) Log.d(TAG, buildLogMsg(message ?: ""))
    }

    /**
     * Log Level Verbose
     */
    fun v(message: String?) {
        if (MyArchitectureApplication.DEBUG) Log.v(TAG, buildLogMsg(message ?: ""))
    }


    fun buildLogMsg(message: String): String {

        val ste = Thread.currentThread().stackTrace[4]

        val sb = StringBuilder()

        sb.append("[")
        sb.append(ste.fileName.replace(".java", ""))
        sb.append("::")
        sb.append(ste.methodName)
        sb.append("]")
        sb.append(message)

        return sb.toString()

    }
}