package com.example.architecture.provider

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes strRes: Int): String
}