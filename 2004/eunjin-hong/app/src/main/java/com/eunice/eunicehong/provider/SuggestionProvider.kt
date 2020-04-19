package com.eunice.eunicehong.provider

import android.content.SearchRecentSuggestionsProvider

class SuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        val AUTHORITY = "com.eunice.eunicehong.provider.SuggestionProvider"
        const val MODE: Int = DATABASE_MODE_QUERIES
    }
}
