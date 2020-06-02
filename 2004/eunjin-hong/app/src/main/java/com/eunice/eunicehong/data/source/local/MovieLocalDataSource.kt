package com.eunice.eunicehong.data.source.local

import android.provider.SearchRecentSuggestions
import com.eunice.eunicehong.data.model.MovieContents
import com.eunice.eunicehong.data.source.MovieDataSource
import com.eunice.eunicehong.ui.MoviePreferences
import com.google.gson.JsonSyntaxException

class MovieLocalDataSource(
    private val sharedPreferences: MoviePreferences,
    private val searchRecentSuggestions: SearchRecentSuggestions
) : MovieDataSource, MovieCache {

    override fun saveMovieList(query: String, movieContents: MovieContents) =
        sharedPreferences.saveHistory(query, movieContents)


    override fun removeMovieHistory() {
        sharedPreferences.removeAllSearchHistory()
    }

    override fun saveSearchRecentSuggestions(query: String) {
        searchRecentSuggestions.saveRecentQuery(query, null)
    }

    override fun deleteAllSearchRecentSuggestions() {
        searchRecentSuggestions.clearHistory()
    }

    @Throws(IllegalStateException::class, JsonSyntaxException::class)
    fun getMovieList(query: String): MovieContents {
        return sharedPreferences.getHistory(query)
    }
}