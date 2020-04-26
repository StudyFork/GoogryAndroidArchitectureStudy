package com.tsdev.tsandroid.ext

import android.content.Context
import android.widget.Toast
import com.tsdev.tsandroid.R

fun Context.showToast(durationTime: Int) =
    Toast.makeText(
        this,
        R.string.occur_error_toast, durationTime
    ).show()