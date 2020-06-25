package com.project.architecturestudy.provider

import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes res: Int): String
}