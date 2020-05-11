package com.example.architecture.data.source.local

import android.content.Context
import com.example.architecture.data.model.MovieModel
import com.example.architecture.util.ConstValue.Companion.MOVIE_SHARED_PREFERENCE_NAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NaverLocalDataSourceImpl : NaverLocalDataSource {

    private fun movieModelToString(movies: List<MovieModel>): String {
        return Gson().toJson(movies)
    }

    private fun stringToMovieModel(gsonString: String): List<MovieModel> {
        val collectionType = object : TypeToken<List<MovieModel>>() {}.type

        return Gson().fromJson(gsonString, collectionType)
    }

    override fun getMovieList(
        context: Context,
        keyword: String,
        onSuccess: (movies: List<MovieModel>) -> Unit
    ) {
        val sharedPref =
            context.getSharedPreferences(MOVIE_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
                ?: return

        val movieString = sharedPref.getString(keyword, null) ?: return
        val movies = stringToMovieModel(movieString)

        onSuccess(movies)
    }

    override fun saveMovieList(
        context: Context,
        keyword: String,
        movies: List<MovieModel>
    ) {
        val sharedPref =
            context.getSharedPreferences(MOVIE_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
                ?: return

        val movieString = movieModelToString(movies)

        sharedPref.edit().apply {
            putString(keyword, movieString)
            apply()
        }

    }

    override fun clearData(context: Context) {
        val sharedPref =
            context.getSharedPreferences(MOVIE_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
                ?: return

        sharedPref.edit().apply {

            clear()
            apply()
        }
    }

}