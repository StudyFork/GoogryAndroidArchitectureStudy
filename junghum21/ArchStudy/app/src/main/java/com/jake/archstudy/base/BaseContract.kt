package com.jake.archstudy.base

import android.widget.Toast
import androidx.annotation.StringRes

interface BaseContract {

    interface View<P> {

        val presenter: P

        fun showToast(
            @StringRes
            stringResId: Int,
            duration: Int = Toast.LENGTH_SHORT
        )

    }

    interface Presenter<V> {

        val view: V

        fun start()

    }

}