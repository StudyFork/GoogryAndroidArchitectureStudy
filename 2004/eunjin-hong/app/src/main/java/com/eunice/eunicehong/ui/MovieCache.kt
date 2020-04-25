package com.eunice.eunicehong.ui

import com.eunice.eunicehong.data.model.MovieList

interface MovieCache {
    fun getMovieList(query: String): MovieList

    fun saveMovieList(query: String, movieList: MovieList)

    fun removeMovieHistory()

}