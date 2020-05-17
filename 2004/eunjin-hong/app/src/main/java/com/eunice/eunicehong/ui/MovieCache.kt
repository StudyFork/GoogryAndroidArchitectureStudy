package com.eunice.eunicehong.ui

import com.eunice.eunicehong.data.model.MovieContents

interface MovieCache {
    fun getMovieList(query: String): MovieContents

    fun saveMovieList(query: String, movieContents: MovieContents)

    fun removeMovieHistory()

    fun saveSearchRecentSuggestions(query: String)

    fun deleteAllSearchRecentSuggestions()

}