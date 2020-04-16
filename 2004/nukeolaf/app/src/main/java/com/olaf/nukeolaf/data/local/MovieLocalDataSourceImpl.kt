package com.olaf.nukeolaf.data.local

import android.content.Context
import com.google.gson.GsonBuilder
import com.olaf.nukeolaf.R
import com.olaf.nukeolaf.data.model.MovieResponse

class MovieLocalDataSourceImpl(context: Context) : MovieLocalDataSource {

    private val name = context.resources.getString(R.string.MOVIE_CACHE_DATA)
    private val sharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)
    private val gsonBuilder = GsonBuilder().create()

    override fun saveMovies(movies: MovieResponse) {
        sharedPreferences.edit().apply {
            putString(name, gsonBuilder.toJson(movies))
            apply()
        }
    }

    override fun getMovies(): MovieResponse? {
        val movies = sharedPreferences.getString(name, null)
        return gsonBuilder.fromJson(movies, MovieResponse::class.java)
    }
}