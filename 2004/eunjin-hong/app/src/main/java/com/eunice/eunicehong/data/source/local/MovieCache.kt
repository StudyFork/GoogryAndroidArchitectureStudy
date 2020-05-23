package com.eunice.eunicehong.data.source.local

import com.eunice.eunicehong.data.model.MovieContents

interface MovieCache {
    fun saveMovieList(query: String, movieContents: MovieContents)

    fun removeMovieHistory()

    fun saveSearchRecentSuggestions(query: String)

    fun deleteAllSearchRecentSuggestions()

}