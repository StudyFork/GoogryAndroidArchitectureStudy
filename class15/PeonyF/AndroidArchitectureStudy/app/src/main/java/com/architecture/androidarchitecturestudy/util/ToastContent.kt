package com.architecture.androidarchitecturestudy.util


import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.architecture.androidarchitecturestudy.R

enum class ToastContent(val msg: Int) {
    SUCCESS(R.string.network_success),
    EMPTY_KEYWORD(R.string.keyword_empty),
    FAIL(R.string.network_error),
    EMPTY_RESULT(R.string.keyword_result_empty);


    fun getToastContent(toastContent: ToastContent) {
        when (toastContent) {
            ToastContent.SUCCESS -> App.context.toast(toastContent.msg)
            ToastContent.EMPTY_KEYWORD -> App.context.toast(toastContent.msg)
            ToastContent.FAIL -> App.context.toast(toastContent.msg)
            ToastContent.EMPTY_RESULT -> App.context.toast(toastContent.msg)
        }
    }

    fun Context.toast(@StringRes stringId: Int) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
    }

}


