package com.tsdev.presentation.provider

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getResultErrorString(@StringRes resId: Int): String

    val getContext: Context
}