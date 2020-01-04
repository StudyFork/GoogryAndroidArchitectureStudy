package com.example.androidarchitecture.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import com.example.androidarchitecture.common.StringConst.Companion.PREF_KEY
import com.example.androidarchitecture.common.StringConst.Companion.PREF_KEY_BLOG
import com.example.androidarchitecture.common.StringConst.Companion.PREF_KEY_IMAGE
import com.example.androidarchitecture.common.StringConst.Companion.PREF_KEY_KIN
import com.example.androidarchitecture.common.StringConst.Companion.PREF_KEY_MOVIE
import com.example.androidarchitecture.data.datasource.database.SearchHistDatabase
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.data.response.MovieData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NaverLocalDataSourceIml(private val context: Context) : NaverLocalDataSourceInterface {

    override val spm: SharedPreferences by lazy {
        context.getSharedPreferences(PREF_KEY, 0)
    }
    override val searchHistyDatabase: SearchHistDatabase by lazy {
        SearchHistDatabase.getInstance(context)
    }


    override fun saveBlogHist(blog: List<BlogData>) {
        CoroutineScope(Dispatchers.IO).launch {
            searchHistyDatabase.blogDao().clearAll()
            searchHistyDatabase.blogDao().insertAll(blog)
        }
    }

    override fun saveMovieHist(movie: List<MovieData>) {
        CoroutineScope(Dispatchers.IO).launch {
            searchHistyDatabase.movieDao().clearAll()
            searchHistyDatabase.movieDao().insertAll(movie)
        }
    }

    override fun saveImageHist(image: List<ImageData>) {
        CoroutineScope(Dispatchers.IO).launch {
            searchHistyDatabase.imageDao().clearAll()
            searchHistyDatabase.imageDao().insertAll(image)
        }
    }

    override fun saveKinHist(kin: List<KinData>) {
        CoroutineScope(Dispatchers.IO).launch {
            searchHistyDatabase.kinDao().clearAll()
            searchHistyDatabase.kinDao().insertAll(kin)
        }
    }

    override suspend fun getBlogHist(): List<BlogData> = searchHistyDatabase.blogDao().getAll()
    override suspend fun getMovieHist(): List<MovieData> = searchHistyDatabase.movieDao().getAll()
    override suspend fun getImageHist(): List<ImageData> = searchHistyDatabase.imageDao().getAll()
    override suspend fun getKinHist(): List<KinData> = searchHistyDatabase.kinDao().getAll()


    override fun saveBlogKeyword(keyword: String) {
        spm.edit()
            .putString(PREF_KEY_BLOG, keyword)
            .apply()
    }

    override fun saveMovieKeyword(keyword: String) {
        spm.edit()
            .putString(PREF_KEY_MOVIE, keyword)
            .apply()
    }

    override fun saveImageKeyword(keyword: String) {
        spm.edit()
            .putString(PREF_KEY_IMAGE, keyword)
            .apply()
    }

    override fun saveKinKeyword(keyword: String) {
        spm.edit()
            .putString(PREF_KEY_KIN, keyword)
            .apply()
    }

    override fun getBlogKeyword(): String = spm.getString(PREF_KEY_BLOG, "") ?: ""
    override fun getMovieKeyword(): String = spm.getString(PREF_KEY_MOVIE, "") ?: ""
    override fun getImageKeyword(): String = spm.getString(PREF_KEY_IMAGE, "") ?: ""
    override fun getKinKeyword(): String = spm.getString(PREF_KEY_KIN, "") ?: ""


}