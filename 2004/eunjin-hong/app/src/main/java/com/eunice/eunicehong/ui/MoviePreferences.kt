package com.eunice.eunicehong.ui

import android.content.Context
import android.content.res.Resources
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.model.MovieList
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException

class MoviePreferences {
    companion object {
        private var instance: MoviePreferences? = null
        private lateinit var context: Context

        fun getInstance(_context: Context): MoviePreferences {
            context = _context
            return instance
                ?: MoviePreferences()
        }
    }

    @Throws(IllegalStateException::class, JsonSyntaxException::class)
    fun getHistory(query: String): MovieList {
        val jsonString = getStringValue(query)
        return GsonBuilder().create().fromJson(jsonString, MovieList::class.java)
    }

    fun saveHistory(query: String, value: MovieList) {
        val jsonString = GsonBuilder().create().toJson(value)
        saveStringValue(query, jsonString)
        saveQueryKey(query)
    }

    fun removeAllSearchHistory() {
        with(getPreferences().edit()) {
            clear()
            commit()
        }
    }

    private fun saveQueryKey(query: String) {
        getPreferences().let { sharedPreferences ->
            val key = context.getString(R.string.preference_movie_search_query_history_keys)
            val queryHistory = sharedPreferences.getStringSet(key, HashSet<String>())
            if (queryHistory?.add(query) == true) {
                saveStringSetValue(key, queryHistory)
            }
        }
    }

    @Throws(Resources.NotFoundException::class)
    private fun getStringValue(key: String) =
        getPreferences().getString(key, "")

    private fun saveStringValue(key: String, value: String) {
        with(getPreferences().edit()) {
            putString(key, value)
            commit()
        }
    }

    private fun saveStringSetValue(key: String, value: Set<String>) {
        with(getPreferences().edit()) {
            putStringSet(key, value)
            commit()
        }
    }

    private fun getPreferences() =
        context.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )

}
