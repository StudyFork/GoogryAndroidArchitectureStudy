package com.olaf.nukeolaf.data.local

import android.content.Context
import com.google.gson.GsonBuilder
import com.olaf.nukeolaf.data.model.MovieResponse

class MovieLocalDataSourceImpl(context: Context) : MovieLocalDataSource {

    private val sharedPreferences =
        context.getSharedPreferences("movies", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    override fun saveMovies(movies: MovieResponse) {
        editor.putString("movies", GsonBuilder().create().toJson(movies))
        editor.commit()
    }

    override fun getMovies(): MovieResponse? {
        val movies = sharedPreferences.getString("movies", null)
        return if (movies != null) {
            GsonBuilder().create().fromJson(movies, MovieResponse::class.java)
        } else {
            null
        }
    }
}