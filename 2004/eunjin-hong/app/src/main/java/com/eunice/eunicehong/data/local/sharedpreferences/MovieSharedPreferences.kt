package com.eunice.eunicehong.data.local.sharedpreferences

import android.content.Context
import android.content.res.Resources
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.model.Movie
import com.eunice.eunicehong.data.model.MovieList
import com.google.gson.GsonBuilder

object MovieSharedPreferences {

    @Throws(NullPointerException::class)
    fun getHistory(context: Context, query: String): List<Movie> {
        val jsonString = getStringValue(context, query)
        return GsonBuilder()
            .create()
            .fromJson(jsonString, MovieList::class.java)
            .items
    }

    fun saveHistory(context: Context, query: String, value: MovieList) {
        val jsonString = GsonBuilder().create().toJson(value)
        saveStringValue(context, query, jsonString)
        saveQueryKey(context, query)
    }

    fun removeAllSearchHistory(context: Context) {
        with(getSharedPreferences(context).edit()) {
            clear()
            commit()
        }
    }

    private fun saveQueryKey(context: Context, query: String) {
        getSharedPreferences(context).let { sharedPreferences ->
            val key = context.getString(R.string.preference_movie_search_query_history_keys)
            val queryHistory = sharedPreferences.getStringSet(key, HashSet<String>())
            if (queryHistory?.add(query) == true) {
                saveStringSetValue(context, key, queryHistory)
            }
        }
    }

    @Throws(Resources.NotFoundException::class)
    private fun getStringValue(context: Context, key: String) =
        getSharedPreferences(context).getString(key, "")

    private fun saveStringValue(context: Context, key: String, value: String) {
        with(getSharedPreferences(context).edit()) {
            putString(key, value)
            commit()
        }
    }

    private fun saveStringSetValue(context: Context, key: String, value: Set<String>) {
        with(getSharedPreferences(context).edit()) {
            putStringSet(key, value)
            commit()
        }
    }

    private fun getSharedPreferences(context: Context) = context.getSharedPreferences(
        context.getString(R.string.preference_file_key), Context.MODE_PRIVATE
    )
}
