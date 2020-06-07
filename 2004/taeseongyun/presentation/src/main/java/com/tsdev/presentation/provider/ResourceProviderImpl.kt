package com.tsdev.presentation.provider

import android.content.Context

internal class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getResultErrorString(resId: Int): String {
        return context.getString(resId)
    }

    override val getContext: Context = context
}