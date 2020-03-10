package io.github.sooakim.data.local.pref

import android.content.Context
import androidx.core.content.edit

class SAPreferencesHelperImpl(
    applicationContext: Context
) : SAPreferencesHelper {
    private val preferences =
        applicationContext.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    override var isAuthRequired: Boolean
        get() = preferences.getBoolean(KEY_AUTH_REQUIRED, true)
        @Synchronized
        set(value) {
            preferences.edit(false) {
                putBoolean(KEY_AUTH_REQUIRED, value)
            }
        }

    override var latestMovieQuery: String
        get() = preferences.getString(KEY_LATEST_MOVIE_QUERY, null) ?: ""
        @Synchronized
        set(value) {
            preferences.edit(false) {
                putString(KEY_LATEST_MOVIE_QUERY, value)
            }
        }

    companion object {
        const val PREF_FILE_NAME = "io.github.sooakim.data.local.pref"

        private const val KEY_AUTH_REQUIRED = "keyAuthRequired"
        private const val KEY_LATEST_MOVIE_QUERY = "keyLatestMovieQuery"
    }
}