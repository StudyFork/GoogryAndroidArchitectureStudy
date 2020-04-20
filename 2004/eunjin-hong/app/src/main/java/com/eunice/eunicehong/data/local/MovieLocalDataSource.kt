package com.eunice.eunicehong.data.local

import android.content.Context
import com.eunice.eunicehong.data.local.sharedpreferences.MovieSharedPreferences
import com.eunice.eunicehong.data.model.Movie
import com.eunice.eunicehong.data.model.MovieList

object MovieLocalDataSource {
    @Throws(NullPointerException::class)
    fun getMovieList(
        context: Context,
        query: String
    ): List<Movie> = MovieSharedPreferences.getHistory(context, query)

    fun saveMovieList(
        context: Context,
        query: String,
        movieList: MovieList
    ) = MovieSharedPreferences.saveHistory(context, query, movieList)

    fun removeMovieHistory(context: Context) {
        MovieSharedPreferences.removeAllSearchHistory(context)
    }
}