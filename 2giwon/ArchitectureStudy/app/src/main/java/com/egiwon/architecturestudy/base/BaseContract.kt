package com.egiwon.architecturestudy.base

import androidx.annotation.StringRes

interface BaseContract {

    interface View {
        fun showToast(text: String)

        fun showToast(@StringRes textResId: Int)

        fun showLoading()

        fun hideLoading()
    }

    interface Presenter {
        fun clearDisposable()
    }

}
