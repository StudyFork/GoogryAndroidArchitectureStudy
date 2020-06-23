package com.project.architecturestudy.Provider

import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes res: Int): String
}