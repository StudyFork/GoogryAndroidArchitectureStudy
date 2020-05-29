package com.example.kyudong3.provider

import android.content.Context
import androidx.annotation.StringRes

class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {
    override fun getString(@StringRes resourceId: Int): String {
        return context.getString(resourceId)
    }
}