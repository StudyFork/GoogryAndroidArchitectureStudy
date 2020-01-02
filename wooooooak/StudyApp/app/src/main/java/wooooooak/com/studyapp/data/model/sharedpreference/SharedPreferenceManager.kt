package wooooooak.com.studyapp.data.model.sharedpreference

import android.content.Context
import androidx.core.content.edit

class SharedPreferenceManager(context: Context) {
    private val preferenceManager = context.getSharedPreferences("STUDY_APP", 0)
    private val BLOG_SEARCH_TITLE = "BLOG_SEARCH_TITLE"
    private val IMAGE_SEARCH_TITLE = "IMAGE_SEARCH_TITLE"
    private val KIN_SEARCH_TITLE = "KIN_SEARCH_TITLE"
    private val MOVIE_SEARCH_TITLE = "MOVIE_SEARCH_TITLE"

    var blogSearchTitle: String
        get() = preferenceManager.getString(BLOG_SEARCH_TITLE, "") ?: ""
        set(value) = preferenceManager.edit {
            putString(BLOG_SEARCH_TITLE, value)
        }

    var imageSearchTitle: String
        get() = preferenceManager.getString(IMAGE_SEARCH_TITLE, "") ?: ""
        set(value) = preferenceManager.edit {
            putString(IMAGE_SEARCH_TITLE, value)
        }

    var kinSearchTitle: String
        get() = preferenceManager.getString(KIN_SEARCH_TITLE, "") ?: ""
        set(value) = preferenceManager.edit {
            putString(KIN_SEARCH_TITLE, value)
        }

    var movieSearchTitle: String
        get() = preferenceManager.getString(MOVIE_SEARCH_TITLE, "") ?: ""
        set(value) = preferenceManager.edit {
            putString(MOVIE_SEARCH_TITLE, value)
        }
}