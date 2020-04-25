package io.github.jesterz91.study.presentation.util

import android.content.Context
import androidx.annotation.StringRes

class ResourceProviderImpl(private val context: Context) : ResourceProvider {

    override fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }
}