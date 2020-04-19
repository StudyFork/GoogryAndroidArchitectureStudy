package io.github.sooakim.local

import io.github.sooakim.data.local.SAAuthLocalDataSource
import io.github.sooakim.local.pref.SAPreferencesHelper

internal class SAAuthLocalDataSourceImpl(
    private val pref: SAPreferencesHelper
) : SAAuthLocalDataSource {
    override val isAuthRequired: Boolean
        get() = pref.isAuthRequired

    override fun saveAuth() {
        pref.isAuthRequired = false
    }
}