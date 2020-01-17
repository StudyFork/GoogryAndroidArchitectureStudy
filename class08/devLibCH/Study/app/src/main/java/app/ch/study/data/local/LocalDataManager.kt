package app.ch.study.data.local

import android.content.Context
import android.content.SharedPreferences

open class LocalDataManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

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

        fun getInstance(context: Context): LocalDataManager? {
            if (staticSharedPrefer == null)
                staticSharedPrefer =
                    LocalDataManager(context)

            return staticSharedPrefer
        }
    }

}