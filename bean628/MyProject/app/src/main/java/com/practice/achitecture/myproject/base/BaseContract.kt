package com.practice.achitecture.myproject.base

import androidx.annotation.StringRes

interface BaseContract {
    interface View {
        fun showToast(message: String)
        fun showToast(@StringRes stringResId: Int)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter
}