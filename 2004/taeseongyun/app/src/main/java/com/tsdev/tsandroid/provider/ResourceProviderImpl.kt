package com.tsdev.tsandroid.provider

import android.content.Context
import com.tsdev.tsandroid.R

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getResultErrorString(resId: Int) =
        context.getString(resId)
}