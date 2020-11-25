package com.example.androidarchitecturestudy

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.androidarchitecturestudy.data.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray


class Preferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(
        "movieList",
        Activity.MODE_PRIVATE
    )
    private var gson = Gson()
    private var editor = preferences.edit()

    fun getMovieTitleList(): ArrayList<String> {
        val str = preferences.getString(TITLE_LIST, null)
        val titleList = arrayListOf<String>()

        if (str != null) {
            if (str.isNotBlank()) {
                val jsonArray = JSONArray(str)
                for (i in 0 until jsonArray.length()) {
                    titleList.add(jsonArray[i].toString())
                }
            }
        }
        return titleList
    }

    fun saveMovieTitle(title: String) {
        val titleList = getMovieTitleList()
        titleList.add(title)
        if (titleList.size > 5) {
            titleList.removeAt(0)
        }
        val jsonArray = JSONArray(titleList)
        editor.putString(TITLE_LIST, jsonArray.toString())
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

    companion object{
        const val TITLE_LIST = "titleList"
        const val MOVIE_LIST = "MovieList"
    }
}