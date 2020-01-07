package com.jay.architecturestudy.data.source.local

import android.content.Context
import android.content.SharedPreferences
import com.jay.architecturestudy.MainApplication
import com.jay.architecturestudy.data.database.SearchHistoryDatabase
import com.jay.architecturestudy.data.database.entity.BlogEntity
import com.jay.architecturestudy.data.database.entity.ImageEntity
import com.jay.architecturestudy.data.database.entity.KinEntity
import com.jay.architecturestudy.data.database.entity.MovieEntity
import com.jay.architecturestudy.data.model.*
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource.Companion.PREFS_KEY_BLOG
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource.Companion.PREFS_KEY_IMAGE
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource.Companion.PREFS_KEY_KIN
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource.Companion.PREFS_KEY_MOVIE
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource.Companion.PREFS_NAME
import io.reactivex.Single

object NaverSearchLocalDataSourceImpl : NaverSearchLocalDataSource {

    private val context: Context
        get() = MainApplication.instance

    override val searchHistoryDatabase: SearchHistoryDatabase by lazy {
        SearchHistoryDatabase.getInstance(context)
    }

    override val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override fun getMovie(): Single<MovieLocalData> =
        searchHistoryDatabase.movieDao()
            .getAll()
            .map {
                MovieLocalData(it)
            }

    override fun getImage(): Single<ImageLocalData> =
        searchHistoryDatabase.imageDao()
            .getAll()
            .map {
                ImageLocalData(it)
            }

    override fun getBlog(): Single<BlogLocalData> =
        searchHistoryDatabase.blogDao()
            .getAll()
            .map {
                BlogLocalData(it)
            }

    override fun getKin(): Single<KinLocalData> =
        searchHistoryDatabase.kinDao()
            .getAll()
            .map {
                KinLocalData(it)
            }

    override fun saveMovieResult(movies: List<MovieEntity>) {
        searchHistoryDatabase.movieDao().insertAll(movies)
    }

    override fun saveImageResult(images: List<ImageEntity>) {
        searchHistoryDatabase.imageDao().insertAll(images)
    }

    override fun saveBlogResult(blogs: List<BlogEntity>) {
        searchHistoryDatabase.blogDao().insertAll(blogs)
    }

    override fun saveKinResult(kins: List<KinEntity>) {
        searchHistoryDatabase.kinDao().insertAll(kins)
    }

    override fun clearMovieResult() {
        searchHistoryDatabase.movieDao().clearAll()
    }

    override fun clearImageResult() {
        searchHistoryDatabase.imageDao().clearAll()
    }

    override fun clearBlogResult() {
        searchHistoryDatabase.blogDao().clearAll()
    }

    override fun clearKinResult() {
        searchHistoryDatabase.kinDao().clearAll()
    }

    override fun saveMovieKeyword(keyword: String) {
        sharedPreferences.edit()
            .putString(PREFS_KEY_MOVIE, keyword)
            .apply()
    }

    override fun saveImageKeyword(keyword: String) {
        sharedPreferences.edit()
            .putString(PREFS_KEY_IMAGE, keyword)
            .apply()
    }

    override fun saveBlogKeyword(keyword: String) {
        sharedPreferences.edit()
            .putString(PREFS_KEY_BLOG, keyword)
            .apply()
    }

    override fun saveKinKeyword(keyword: String) {
        sharedPreferences.edit()
            .putString(PREFS_KEY_KIN, keyword)
            .apply()
    }

    override fun getLatestMovieKeyword(): String =
        sharedPreferences.getString(PREFS_KEY_MOVIE, "") ?: ""

    override fun getLatestImageKeyword(): String =
        sharedPreferences.getString(PREFS_KEY_IMAGE, "") ?: ""

    override fun getLatestBlogKeyword(): String =
        sharedPreferences.getString(PREFS_KEY_BLOG, "") ?: ""

    override fun getLatestKinKeyword(): String =
        sharedPreferences.getString(PREFS_KEY_KIN, "") ?: ""

    override fun clearMovieKeyword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearImageKeyword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearBlogKeyword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearKinKeyword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
