package com.tsdev.tsandroid.provider

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getResultErrorString(@StringRes resId: Int): String
}