package app.ch.study.data.local

import android.content.SharedPreferences

open class LocalDataManager(private val prefs: SharedPreferences) {

    fun saveMovieList(list: String) {
        val editor = prefs.edit()
        editor.putString("movieList", list)
        editor.apply()
    }

    fun searchMovieList(): String = prefs.getString("movieList", "") ?: ""

    fun saveSearchQuery(query: String) {
        val editor = prefs.edit()
        editor.putString("query", query)
        editor.apply()
    }

    fun getQuery(): String = prefs.getString("query", "") ?: ""

    companion object {
        private var staticSharedPrefer: LocalDataManager? = null

        fun getInstance(prefs: SharedPreferences): LocalDataManager {
            if (staticSharedPrefer == null)
                staticSharedPrefer = LocalDataManager(prefs)

            return staticSharedPrefer!!
        }
    }

}