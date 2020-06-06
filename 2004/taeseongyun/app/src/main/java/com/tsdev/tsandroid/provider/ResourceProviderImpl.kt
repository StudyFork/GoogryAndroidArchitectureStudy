package com.tsdev.tsandroid.provider

import android.content.Context

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getResultErrorString(resId: Int) =
        context.getString(resId)

    override val getContext: Context
        get() = context
}