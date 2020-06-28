package com.chul.data.source.local

import android.content.Context
import android.content.SharedPreferences
import com.chul.data.model.MovieModel
import com.chul.data.util.AppUtil.convertGsonToJson
import com.chul.data.util.AppUtil.convertJsonToGson
import com.chul.data.util.ConstValue.MOVIE_SHARED_PREFERENCE_NAME

internal class NaverLocalDataSourceImpl(private val context: Context) : NaverLocalDataSource {

    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences(
            MOVIE_SHARED_PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }

    override fun getMovieList(
        keyword: String,
        onSuccess: (movieList: List<MovieModel>) -> Unit
    ) {
        val jsonMovieData = sharedPref.getString(keyword, null) ?: return
        val movieList = convertJsonToGson<MovieModel>(jsonMovieData)

        onSuccess(movieList.toList())
    }

    override fun saveMovieList(
        keyword: String,
        movieList: List<MovieModel>
    ) {

        val jsonMovieData = convertGsonToJson(movieList)

        sharedPref.edit()
            .putString(keyword, jsonMovieData)
            .apply()
    }

    override fun clearData() {
        sharedPref.edit()
            .clear()
            .apply()
    }

}
