package com.tsdev.tsandroid.ext

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes toastText: Int, durationTime: Int) =
    Toast.makeText(
        this,
        toastText, durationTime
    ).show()