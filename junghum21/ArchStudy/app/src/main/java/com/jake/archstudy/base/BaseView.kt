package com.jake.archstudy.base

import android.widget.Toast
import androidx.annotation.StringRes

interface BaseView<P> {

    val presenter: P

    fun showToast(
        text: String,
        duration: Int = Toast.LENGTH_SHORT
    )

    fun showToast(
        @StringRes
        stringResId: Int,
        duration: Int = Toast.LENGTH_SHORT
    )

}