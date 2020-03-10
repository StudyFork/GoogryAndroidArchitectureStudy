package io.github.sooakim.data.local.source

import io.github.sooakim.data.local.pref.SAPreferencesHelper

class SAAuthLocalDataSourceImpl(
    private val pref: SAPreferencesHelper
) : SAAuthLocalDataSource {
    override val isAuthRequired: Boolean
        get() = pref.isAuthRequired

    override fun saveAuth() {
        pref.isAuthRequired = false
    }
}