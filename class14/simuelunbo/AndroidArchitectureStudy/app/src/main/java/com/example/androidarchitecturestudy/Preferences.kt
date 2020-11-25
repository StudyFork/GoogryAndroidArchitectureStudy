package com.example.androidarchitecturestudy

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.QueryHistory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray


class Preferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(
        MOVIE_LIST,
        Activity.MODE_PRIVATE
    )
    private var gson = Gson()
    private var editor = preferences.edit()

    fun getMovieQueryList(): ArrayList<QueryHistory> {
        val prefsTitle = preferences.getString(TITLE_LIST, null)
        var titleList = arrayListOf<QueryHistory>()

        if (prefsTitle != null) {
            val jsonArray = JSONArray(prefsTitle)
            val type = object : TypeToken<ArrayList<QueryHistory?>?>() {}.type
            titleList = gson.fromJson(prefsTitle, type)
        }
        return titleList
    }

    fun saveMovieQuery(title: String) {
        val titleList = getMovieQueryList()
        titleList.add(QueryHistory(title))
        if (titleList.size > 5) {
            titleList.removeAt(0)
        }
        var query = gson.toJson(titleList)
        editor.putString(TITLE_LIST, query.toString())
        editor.commit()
    }

    fun saveMovieList(data: List<Movie>) {
        var movie = gson.toJson(data)
        editor.putString(MOVIE_LIST, movie)
        editor.commit()
    }

    fun getMovieList(): List<Movie>? {
        var movie = preferences.getString(MOVIE_LIST, null)
        var movieList = listOf<Movie>()
        val type = object : TypeToken<List<Movie?>?>() {}.type
        if (movie != null) {
            movieList = gson.fromJson(movie, type)
        }
        return movieList
    }

    companion object {
        const val TITLE_LIST = "titleList"
        const val MOVIE_LIST = "MovieList"
    }
}