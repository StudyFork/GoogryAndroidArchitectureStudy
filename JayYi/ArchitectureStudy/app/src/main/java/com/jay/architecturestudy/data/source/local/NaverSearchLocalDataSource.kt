package com.jay.architecturestudy.data.source.local

import android.content.SharedPreferences
import com.jay.architecturestudy.data.database.SearchHistoryDatabase
import com.jay.architecturestudy.data.database.entity.BlogEntity
import com.jay.architecturestudy.data.database.entity.ImageEntity
import com.jay.architecturestudy.data.database.entity.KinEntity
import com.jay.architecturestudy.data.database.entity.MovieEntity
import com.jay.architecturestudy.data.model.BlogLocalData
import com.jay.architecturestudy.data.model.ImageLocalData
import com.jay.architecturestudy.data.model.KinLocalData
import com.jay.architecturestudy.data.model.MovieLocalData
import io.reactivex.Single

interface NaverSearchLocalDataSource {
    val searchHistoryDatabase: SearchHistoryDatabase
    val sharedPreferences: SharedPreferences

    fun getMovie(): Single<MovieLocalData>

    fun getImage(): Single<ImageLocalData>

    fun getBlog(): Single<BlogLocalData>

    fun getKin(): Single<KinLocalData>

    fun saveMovieResult(movies: List<MovieEntity>)

    fun saveImageResult(images: List<ImageEntity>)

    fun saveBlogResult(blogs: List<BlogEntity>)

    fun saveKinResult(kins: List<KinEntity>)

    fun clearMovieResult()

    fun clearImageResult()

    fun clearBlogResult()

    fun clearKinResult()

    fun saveMovieKeyword(keyword: String)

    fun saveImageKeyword(keyword: String)

    fun saveBlogKeyword(keyword: String)

    fun saveKinKeyword(keyword: String)

    fun getLatestMovieKeyword(): String

    fun getLatestImageKeyword(): String

    fun getLatestBlogKeyword(): String

    fun getLatestKinKeyword(): String

    fun clearMovieKeyword()

    fun clearImageKeyword()

    fun clearBlogKeyword()

    fun clearKinKeyword()

    companion object {
        const val PREFS_NAME = "search_history"
        const val PREFS_KEY_MOVIE = "movie"
        const val PREFS_KEY_BLOG = "blog"
        const val PREFS_KEY_IMAGE = "image"
        const val PREFS_KEY_KIN = "kin"
    }
}