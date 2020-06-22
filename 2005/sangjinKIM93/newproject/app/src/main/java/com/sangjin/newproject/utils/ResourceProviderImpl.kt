package com.sangjin.newproject.utils

import android.app.Application

class ResourceProviderImpl(private val context: Application) : ResourceProvider {
    override fun getString(strRes: Int): String {
        return context.getString(strRes)
    }
}