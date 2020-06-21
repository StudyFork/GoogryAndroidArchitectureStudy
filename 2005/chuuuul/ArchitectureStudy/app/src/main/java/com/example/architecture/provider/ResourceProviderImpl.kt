package com.example.architecture.provider

import android.content.Context
import androidx.annotation.StringRes

class ResourceProviderImpl(private val context: Context) : ResourceProvider {

    override fun getString(@StringRes strRes: Int): String {
        return context.getString(strRes)
    }
}