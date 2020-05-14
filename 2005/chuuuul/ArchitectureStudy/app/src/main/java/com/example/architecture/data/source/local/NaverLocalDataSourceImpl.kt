package com.example.architecture.data.source.local

import android.content.Context
import com.example.architecture.data.model.MovieModel
import com.example.architecture.util.AppUtil.convertGsonToJson
import com.example.architecture.util.AppUtil.convertJsonToGson
import com.example.architecture.util.ConstValue.Companion.MOVIE_SHARED_PREFERENCE_NAME

class NaverLocalDataSourceImpl : NaverLocalDataSource {

    override fun getMovieList(
        context: Context,
        keyword: String,
        onSuccess: (movieList: List<MovieModel>) -> Unit
    ) {
        val sharedPref =
            context.getSharedPreferences(MOVIE_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
                ?: return

        val jsonMovieData = sharedPref.getString(keyword, null) ?: return
        val movieList = convertJsonToGson<MovieModel>(jsonMovieData)

        onSuccess(movieList)
    }

    override fun saveMovieList(
        context: Context,
        keyword: String,
        movieList: List<MovieModel>
    ) {
        val sharedPref =
            context.getSharedPreferences(MOVIE_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
                ?: return

        val jsonMovieData = convertGsonToJson(movieList)

        sharedPref.edit().apply {
            putString(keyword, jsonMovieData)
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