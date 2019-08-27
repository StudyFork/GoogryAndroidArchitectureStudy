package com.jake.archstudy.ext

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes

fun Activity.toast(
    text: String,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(this, text, duration).show()
}

fun Activity.toast(
    @StringRes stringRes: Int,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(this, stringRes, duration).show()
}
