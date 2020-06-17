package com.example.architecture.data.source.local

import android.content.Context
import android.content.SharedPreferences
import com.example.architecture.data.model.MovieModel
import com.example.architecture.util.AppUtil.convertGsonToJson
import com.example.architecture.util.AppUtil.convertJsonToGson
import com.example.architecture.util.ConstValue.Companion.MOVIE_SHARED_PREFERENCE_NAME

class NaverLocalDataSourceImpl(private val context: Context) : NaverLocalDataSource {

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

    companion object {
        private var INSTANCE: NaverLocalDataSourceImpl? = null

        @JvmStatic
        fun getInstance(context: Context): NaverLocalDataSourceImpl {
            synchronized(NaverLocalDataSourceImpl::javaClass) {
                if (INSTANCE == null) {
                    INSTANCE = NaverLocalDataSourceImpl(context)
                }
                return INSTANCE!!
            }
        }
    }
}
