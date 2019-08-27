package com.jake.archstudy.ext

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(
    text: String,
    duration: Int = Toast.LENGTH_SHORT
) {
    activity?.toast(text, duration)
}

fun Fragment.toast(
    @StringRes stringRes: Int,
    duration: Int = Toast.LENGTH_SHORT
) {
    activity?.toast(stringRes, duration)
}
