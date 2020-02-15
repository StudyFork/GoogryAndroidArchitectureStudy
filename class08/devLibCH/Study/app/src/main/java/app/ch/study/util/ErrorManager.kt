package app.ch.study.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import app.ch.study.R
import app.ch.study.data.common.KEY_QUERY_EMPTY
import app.ch.study.data.remote.api.WebApiDefine
import retrofit2.HttpException

fun handleError(context: Context, key: String?) {
    when (key) {
        KEY_QUERY_EMPTY -> {
            showLongMsg(
                context,
                context.getString(R.string.empty_query)
            )
        }
        else -> {
            showLongMsg(
                context,
                context.getString(R.string.unknown_error)
            )
        }
    }
}

private fun showLongMsg(context: Context, msg: String) =
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()

fun handleError(e: Throwable): String {
    if (e is HttpException) {
        val code = e.code()
        WebApiDefine.showErrorLog(code)
    } else {
        Log.e("HttpErrorHandler", e.toString())
    }

    return ((e as? Exception)?.message) ?: ""
}