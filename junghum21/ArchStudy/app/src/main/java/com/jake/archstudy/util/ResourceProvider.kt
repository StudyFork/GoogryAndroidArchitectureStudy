package com.jake.archstudy.util

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(
        @StringRes
        stringResId: Int
    ): String

}

class ResourceProviderImpl(private val context: Context) : ResourceProvider {

    override fun getString(stringResId: Int): String = context.getString(stringResId)

}