package com.tsdev.tsandroid

import android.content.Context
import android.widget.Toast

fun Context.showToast(durationTime: Int) =
    Toast.makeText(this, R.string.occur_error_toast, durationTime).show()
