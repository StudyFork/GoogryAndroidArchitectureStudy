package app.ch.study.data.local

import android.content.Context
import android.content.SharedPreferences

open class LocalDataManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    companion object {
        private var staticSharedPrefer: LocalDataManager? = null

        fun getInstance(context: Context): LocalDataManager? {
            if (staticSharedPrefer == null)
                staticSharedPrefer =
                    LocalDataManager(context)

            return staticSharedPrefer
        }
    }

    fun saveMovieList(list: String) {
        val editor = prefs.edit()
        editor.putString("movieList", list)
        editor.apply()
    }

    fun searchMovieList(): String = prefs.getString("contactId", "") ?: ""

}