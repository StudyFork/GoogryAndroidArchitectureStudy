package com.project.architecturestudy.components

import androidx.annotation.StringRes
import com.project.architecturestudy.CustomApp

object Strings {
    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return CustomApp.instance.getString(stringRes, *formatArgs)
    }
}