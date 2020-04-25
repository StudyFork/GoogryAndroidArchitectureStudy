package io.github.jesterz91.study.presentation.util

import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes resId: Int): String
}